-- Banco de Dados II
-- Gestão de Relacionamento com o Cliente (CRM) e Marketing

-- Código:

CREATE TABLE clientes_crm (
    id_cliente SERIAL PRIMARY KEY,
    nome_completo VARCHAR(120) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    status_lead VARCHAR(30) DEFAULT 'NOVO', -- Ex: NOVO, CONTATADO, QUALIFICADO
    data_criacao TIMESTAMP DEFAULT NOW(),
    data_ultimo_contato TIMESTAMP,
    responsavel_conta VARCHAR(80)
);
CREATE TABLE interacoes_crm (
    id_interacao SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES clientes_crm(id_cliente),
    data_interacao TIMESTAMP DEFAULT NOW(),
    tipo_interacao VARCHAR(50), -- Ex: EMAIL_ENVIADO, LIGACAO_FEITA
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
    canal_principal VARCHAR(40) -- Ex: EMAIL_MARKETING, GOOGLE_ADS, SOCIAL_MEDIA
);
CREATE TABLE leads_por_campanha (
    id_associacao SERIAL PRIMARY KEY,
    id_campanha INT REFERENCES campanhas_mkt(id_campanha),
    id_cliente_crm INT REFERENCES clientes_crm(id_cliente), -- Cliente que se tornou lead via
    campanha
    data_captura_lead TIMESTAMP DEFAULT NOW(),
    origem_especifica_lead VARCHAR(100) -- Ex: FORMULARIO_SITE_CAMPANHA_X
);
CREATE TABLE log_alteracao_status_lead (
    id_log SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL,
    status_anterior VARCHAR(30),
    status_novo VARCHAR(30),
    data_alteracao TIMESTAMP DEFAULT NOW(),
    alterado_por VARCHAR(80), -- Pode ser o nome do usuário ou 'SISTEMA'
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
    -- Primeiro insere a interação
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
    
    -- Depois atualiza a data do último contato
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
LANGUAGE SQL
AS $$
    INSERT INTO log_alteracao_status_lead (
        id_cliente, 
        status_anterior, 
        status_novo, 
        alterado_por,
        motivo_alteracao
    )
    SELECT 
        id_cliente, 
        status_lead, 
        p_novo_status, 
        current_user,
        COALESCE(p_motivo, 'Atualização de status via sistema')
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente AND status_lead <> p_novo_status;
    
    UPDATE clientes_crm 
    SET status_lead = p_novo_status
    WHERE id_cliente = p_id_cliente;
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
BEGIN TRANSACTION
    SELECT status_lead INTO v_status_atual
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente;
    
    -- Verifica se é um downgrade não permitido
    IF v_status_atual = 'QUALIFICADO' AND 
        (p_novo_status = 'NOVO' OR p_novo_status = 'CONTATADO') THEN
        RAISE EXCEPTION 'Não é permitido reverter um lead QUALIFICADO para NOVO ou CONTATADO';
    END IF;
    
    -- Se passou na validação, atualiza normalmente
    CALL atualizar_status_lead(p_id_cliente, p_novo_status, p_motivo);
END;
$$;

-- 4. View de Leads Ativos por Campanha

-- View original (igual à anterior)
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

-- Procedure para "arquivar" um lead da view
CREATE OR REPLACE PROCEDURE arquivar_lead_marketing(
    p_id_cliente INT
)
LANGUAGE SQL
AS $$
    -- Atualiza o status do lead
    UPDATE clientes_crm 
    SET status_lead = 'ARQUIVADO_MARKETING'
    WHERE id_cliente = p_id_cliente;
    
    -- Registra a alteração no log
    INSERT INTO log_alteracao_status_lead (
        id_cliente, 
        status_anterior, 
        status_novo, 
        alterado_por,
        motivo_alteracao
    )
    SELECT 
        id_cliente, 
        status_lead, 
        'ARQUIVADO_MARKETING', 
        current_user,
        'ARQUIVADO_VIA_PROCEDURE_MKT'
    FROM clientes_crm
    WHERE id_cliente = p_id_cliente;
$$;

-- Consultas de Rules:

-- Rule 1)

INSERT INTO interacoes_crm (id_cliente, tipo_interacao, descricao, resultado_interacao)
VALUES (1, 'EMAIL_ENVIADO', 'Envio de proposta', 'AGUARDANDO_RETORNO');

-- Rule 2)

UPDATE clientes_crm SET status_lead = 'QUALIFICADO' WHERE id_cliente = 1;

-- Rule 3)

UPDATE clientes_crm SET status_lead = 'NOVO' WHERE id_cliente = 1;

-- Rule 4)

DELETE FROM vw_leads_ativos_campanha WHERE id_cliente = 1;
