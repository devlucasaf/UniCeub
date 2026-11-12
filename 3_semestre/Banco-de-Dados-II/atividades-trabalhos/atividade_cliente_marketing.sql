CREATE TABLE clientes_crm (
    id_cliente SERIAL PRIMARY KEY,
    nome_completo VARCHAR(120) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    status_lead VARCHAR(30) DEFAULT 'NOVO', 
    data_criacao TIMESTAMP DEFAULT NOW(),
    data_ultimo_contato TIMESTAMP,
    responsavel_conta VARCHAR(80)
);

CREATE TABLE interacoes_crm (
    id_interacao SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES clientes_crm(id_cliente),
    data_interacao TIMESTAMP DEFAULT NOW(),
    tipo_interacao VARCHAR(50), 
    descricao TEXT,
    resultado_interacao VARCHAR(100)
);

CREATE TABLE campanhas_mkt (
    id_campanha SERIAL PRIMARY KEY,
    nome_campanha VARCHAR(100) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    orcamento_total DECIMAL(12, 2),
    publico_alvo TEXT,
    canal_principal VARCHAR(40) 
);

CREATE TABLE leads_por_campanha (
    id_associacao SERIAL PRIMARY KEY,
    id_campanha INT REFERENCES campanhas_mkt(id_campanha),
    id_cliente_crm INT REFERENCES clientes_crm(id_cliente), 
    data_captura_lead TIMESTAMP DEFAULT NOW(),
    origem_especifica_lead VARCHAR(100) 
);

CREATE TABLE log_alteracao_status_lead (
    id_log SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL,
    status_anterior VARCHAR(30),
    status_novo VARCHAR(30),
    data_alteracao TIMESTAMP DEFAULT NOW(),
    alterado_por VARCHAR(80), 
    motivo_alteracao TEXT
);

-- 1. Atualização Automática de "Último Contato" no CRM

CREATE OR REPLACE PROCEDURE registrar_interacao(
    p_id_cliente INT,
    p_tipo_interacao VARCHAR(50),
    p_descricao TEXT,
    p_resultado_interacao VARCHAR(100)
)
LANGUAGE SQL
AS $$
    INSERT INTO interacoes_crm (
        id_cliente, 
        tipo_interacao, 
        descricao, 
        resultado_interacao
    ) VALUES (
        p_id_cliente, 
        p_tipo_interacao, 
        p_descricao, 
        p_resultado_interacao
    );

    UPDATE clientes_crm 
    SET data_ultimo_contato = NOW()
    WHERE id_cliente = p_id_cliente;
$$;

-- 2. Registro de Mudança de Status de Lead

CREATE OR REPLACE PROCEDURE atualizar_status_lead(
    p_id_cliente INT,
    p_novo_status VARCHAR(30),
    p_motivo TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_status_atual VARCHAR(30);
BEGIN
    SELECT status_lead
    INTO v_status_atual
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente;

    IF v_status_atual IS DISTINCT FROM p_novo_status THEN
        INSERT INTO log_alteracao_status_lead (
            id_cliente, 
            status_anterior, 
            status_novo, 
            alterado_por,
            motivo_alteracao
        )
        VALUES (
            p_id_cliente, 
            v_status_atual, 
            p_novo_status, 
            CURRENT_USER,
            COALESCE(p_motivo, 'Atualização de status via sistema')
        );
        
        UPDATE clientes_crm 
        SET status_lead = p_novo_status
        WHERE id_cliente = p_id_cliente;
    END IF;
END;
$$;

-- 3. Prevenção de Downgrade de Lead Qualificado

CREATE OR REPLACE PROCEDURE atualizar_status_seguro(
    p_id_cliente INT,
    p_novo_status VARCHAR(30),
    p_motivo TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_status_atual VARCHAR(30);
BEGIN
    SELECT status_lead
    INTO v_status_atual
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente;

    IF v_status_atual = 'QUALIFICADO' AND 
       p_novo_status IN ('NOVO', 'CONTATADO') THEN
        RAISE EXCEPTION 
         'Não é permitido reverter um lead QUALIFICADO para %',
        p_novo_status;
    END IF;

    CALL atualizar_status_lead(p_id_cliente, p_novo_status, p_motivo);
END;
$$;

-- 4. View de Leads Ativos por Campanha

CREATE OR REPLACE VIEW vw_leads_ativos_campanha AS
SELECT 
    cm.nome_campanha,
    cc.nome_completo,
    cc.email,
    cc.status_lead,
    cc.id_cliente,
    cm.id_campanha
FROM 
    clientes_crm cc
JOIN 
    leads_por_campanha lpc ON cc.id_cliente = lpc.id_cliente_crm
JOIN 
    campanhas_mkt cm ON lpc.id_campanha = cm.id_campanha
WHERE 
    cc.status_lead NOT IN ('PERDIDO', 'GANHO');

CREATE OR REPLACE PROCEDURE arquivar_lead_marketing(
    p_id_cliente INT
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_status_atual VARCHAR(30);
BEGIN
    SELECT status_lead
    INTO v_status_atual
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente;
    
    UPDATE clientes_crm 
    SET status_lead = 'ARQUIVADO_MARKETING'
    WHERE id_cliente = p_id_cliente;
    
    INSERT INTO log_alteracao_status_lead (
        id_cliente, 
        status_anterior, 
        status_novo, 
        alterado_por,
        motivo_alteracao
    )
    VALUES (
        p_id_cliente, 
        v_status_atual, 
        'ARQUIVADO_MARKETING', 
        CURRENT_USER,
        'ARQUIVADO_VIA_PROCEDURE_MKT'
    );
END;
$$;
