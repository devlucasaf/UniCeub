const CHAVE_ARMAZENAMENTO = "moodtrack-registros";

const CORES_HUMOR = {
    1: "#e57373",
    2: "#ffb74d",
    3: "#fff176",
    4: "#aed581",
    5: "#4fc3f7",
};

// Ícones SVG — mesmo desenho usado nos botões do index.html
const baseRosto = (boca, extra = "") => `
    <svg viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <circle cx="12" cy="12" r="10"></circle>
        ${extra}
        <path d="${boca}"></path>
    </svg>`;

const ICONES_HUMOR = {
    5: baseRosto(
        "M6.5 13.5c1.5 3 3.5 4 5.5 4s4-1 5.5-4",
        '<circle cx="8.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle><circle cx="15.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle>'
    ),
    4: baseRosto(
        "M8 14c1 1.5 2.5 2.3 4 2.3s3-0.8 4-2.3",
        '<circle cx="8.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle><circle cx="15.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle>'
    ),
    3: baseRosto(
        "M8 15h8",
        '<circle cx="8.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle><circle cx="15.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle>'
    ),
    2: baseRosto(
        "M8 16c1-1.5 2.5-2.3 4-2.3s3 0.8 4 2.3",
        '<circle cx="8.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle><circle cx="15.5" cy="9.5" r="1" fill="currentColor" stroke="none"></circle>'
    ),
    1: baseRosto(
        "M7 16.5c1.5-3 3.5-4 5-4s3.5 1 5 4",
        '<path d="M7 7.5l2.5 1.5"></path><path d="M17 7.5l-2.5 1.5"></path><circle cx="8.5" cy="10.5" r="1" fill="currentColor" stroke="none"></circle><circle cx="15.5" cy="10.5" r="1" fill="currentColor" stroke="none"></circle>'
    ),
};

const botoesHumor           = document.querySelectorAll(".botao-humor");
const campoNota             = document.getElementById("nota");
const botaoSalvar           = document.getElementById("botao-salvar");
const mensagemFeedback      = document.getElementById("mensagem-feedback");
const botaoLimpar           = document.getElementById("botao-limpar");
const listaHistorico        = document.getElementById("lista-historico");
const canvasGrafico         = document.getElementById("grafico-humor");
const graficoVazio          = document.getElementById("grafico-vazio");

const estatisticaSequencia  = document.getElementById("estatistica-sequencia");
const estatisticaMedia      = document.getElementById("estatistica-media");
const estatisticaTotal      = document.getElementById("estatistica-total");

let humorSelecionado        = null;

function chaveHoje() {
    return new Date().toISOString().slice(0, 10); 
}

function carregarRegistros() {
    try {
        const bruto = localStorage.getItem(CHAVE_ARMAZENAMENTO);
        return bruto ? JSON.parse(bruto) : [];
    } catch {
        return [];
    }
}

function salvarRegistros(registros) {
    localStorage.setItem(CHAVE_ARMAZENAMENTO, JSON.stringify(registros));
}

function formatarData(chaveData) {
    const [a, m, d] = chaveData.split("-");
    return `${d}/${m}/${a}`;
}

// ---- Seleção de humor ----
botoesHumor.forEach((botao) => {
    botao.addEventListener("click", () => {
        botoesHumor.forEach((b) => {
            b.classList.remove("selecionado");
            b.setAttribute("aria-checked", "false");
        });
        botao.classList.add("selecionado");
        botao.setAttribute("aria-checked", "true");
        humorSelecionado = Number(botao.dataset.humor);
        botaoSalvar.disabled = false;
    });
});

// ---- Salvar check-in ----
botaoSalvar.addEventListener("click", () => {
    if (!humorSelecionado) {
        return;
    }

    const registros = carregarRegistros();
    const chave = chaveHoje();
    const indiceExistente = registros.findIndex((r) => r.data === chave);
    const registro = {
        data: chave,
        humor: humorSelecionado,
        nota: campoNota.value.trim(),
        timestamp: Date.now(),
    };

    if (indiceExistente >= 0) {
        registros[indiceExistente] = registro;
    } else {
        registros.push(registro);
    }

    salvarRegistros(registros);
    mostrarFeedback(indiceExistente >= 0 ? "Check-in de hoje atualizado!" : "Check-in salvo com sucesso!");
    renderizar();
});

function mostrarFeedback(mensagem) {
    mensagemFeedback.textContent = mensagem;
    setTimeout(() => {
        mensagemFeedback.textContent = "";
    }, 3000);
}

// ---- Limpar tudo ----
botaoLimpar.addEventListener("click", () => {
    if (confirm("Tem certeza que deseja apagar todo o histórico?")) {
        localStorage.removeItem(CHAVE_ARMAZENAMENTO);
        renderizar();
    }
});

// ---- Excluir um registro ----
function excluirRegistro(data) {
    const registros = carregarRegistros().filter((r) => r.data !== data);
    salvarRegistros(registros);
    renderizar();
}

// ---- Estatísticas ----
function calcularEstatisticas(registros) {
    const total = registros.length;

    const conjuntoDatas = new Set(registros.map((r) => r.data));
    let sequencia = 0;
    let cursor = new Date();
    while (true) {
        const chave = cursor.toISOString().slice(0, 10);
        if (conjuntoDatas.has(chave)) {
            sequencia++;
            cursor.setDate(cursor.getDate() - 1);
        } else {
            break;
        }
    }

    // média dos últimos 7 dias com registro
    const ordenados = [...registros].sort((a, b) => (a.data < b.data ? 1 : -1));
    const ultimos7 = ordenados.slice(0, 7);
    const media =
        ultimos7.length > 0
        ? (ultimos7.reduce((soma, r) => soma + r.humor, 0) / ultimos7.length).toFixed(1)
        : "–";

    return { sequencia, media, total };
}

function renderizarEstatisticas(registros) {
    const { sequencia, media, total } = calcularEstatisticas(registros);
    estatisticaSequencia.textContent = sequencia;
    estatisticaMedia.textContent = media;
    estatisticaTotal.textContent = total;
}

// ---- Histórico ----
function renderizarHistorico(registros) {
    listaHistorico.innerHTML = "";
    const ordenados = [...registros].sort((a, b) => (a.data < b.data ? 1 : -1));

    if (ordenados.length === 0) {
        const item = document.createElement("li");
        item.className = "mensagem-vazia";
        item.textContent = "Nenhum registro ainda.";
        listaHistorico.appendChild(item);
        return;
    }

    ordenados.forEach((registro) => {
        const item = document.createElement("li");
        item.className = "item-historico";
        item.innerHTML = `
            <span class="icone-historico cor-humor-${registro.humor}">${ICONES_HUMOR[registro.humor]}</span>
            <div class="conteudo-historico">
                <div class="data-historico">${formatarData(registro.data)}</div>
                ${registro.nota ? `<div class="nota-historico">${escaparHtml(registro.nota)}</div>` : ""}
            </div>
            <button class="botao-excluir-historico" title="Excluir" aria-label="Excluir registro de ${formatarData(registro.data)}">✕</button>
        `;
        item.querySelector(".botao-excluir-historico").addEventListener("click", () => excluirRegistro(registro.data));
        listaHistorico.appendChild(item);
    });
}

function escaparHtml(texto) {
    const div = document.createElement("div");
    div.textContent = texto;
    return div.innerHTML;
}

// ---- Gráfico ----
function renderizarGrafico(registros) {
    const contexto = canvasGrafico.getContext("2d");
    const largura = canvasGrafico.width;
    const altura = canvasGrafico.height;
    contexto.clearRect(0, 0, largura, altura);

    // últimos 14 dias 
    const dias = [];
    const cursor = new Date();
    for (let i = 13; i >= 0; i--) {
        const d = new Date(cursor);
        d.setDate(cursor.getDate() - i);
        dias.push(d.toISOString().slice(0, 10));
    }

    const mapaRegistros = new Map(registros.map((r) => [r.data, r]));
    const temAlgum = dias.some((d) => mapaRegistros.has(d));

    graficoVazio.hidden = temAlgum;
    canvasGrafico.hidden = !temAlgum;
    if (!temAlgum) {
        return;
    }

    const preenchimento = { 
        topo: 20, 
        direita: 20, 
        baixo: 30, 
        esquerda: 30 
    };
    const larguraPlot = largura - preenchimento.esquerda - preenchimento.direita;
    const alturaPlot = altura - preenchimento.topo - preenchimento.baixo;
    const passoX = larguraPlot / (dias.length - 1);

    // linhas de grade horizontais 
    contexto.strokeStyle = "#e5e7eb";
    contexto.lineWidth = 1;
    contexto.font = "10px sans-serif";
    contexto.fillStyle = "#6b7280";
    for (let m = 1; m <= 5; m++) {
        const y = preenchimento.topo + alturaPlot - ((m - 1) / 4) * alturaPlot;
        contexto.beginPath();
        contexto.moveTo(preenchimento.esquerda, y);
        contexto.lineTo(largura - preenchimento.direita, y);
        contexto.stroke();
        contexto.fillText(String(m), 6, y + 3);
    }

    // pontos e linha conectando dias com registro
    const pontos = dias.map((data, i) => {
        const registro = mapaRegistros.get(data);
        const x = preenchimento.esquerda + i * passoX;
        const y = registro
        ? preenchimento.topo + alturaPlot - ((registro.humor - 1) / 4) * alturaPlot
        : null;
        return { x, y, registro, data };
    });

    contexto.strokeStyle = "#6c63ff";
    contexto.lineWidth = 2;
    contexto.beginPath();
    let iniciado = false;
    pontos.forEach((ponto) => {
        if (ponto.y === null) {
            iniciado = false;
            return;
        }

        if (!iniciado) {
            contexto.moveTo(ponto.x, ponto.y);
            iniciado = true;
        } else {
            contexto.lineTo(ponto.x, ponto.y);
        }
    });
    contexto.stroke();

    pontos.forEach((ponto) => {
        if (ponto.y === null) return;
        contexto.fillStyle = CORES_HUMOR[ponto.registro.humor];
        contexto.beginPath();
        contexto.arc(ponto.x, ponto.y, 5, 0, Math.PI * 2);
        contexto.fill();
        contexto.strokeStyle = "#fff";
        contexto.lineWidth = 1.5;
        contexto.stroke();
    });

    // rótulos de data 
    contexto.fillStyle = "#6b7280";
    contexto.font = "9px sans-serif";
    pontos.forEach((ponto, i) => {
        if (i % 2 !== 0) {
            return;
        }
        const rotulo = ponto.data.slice(5).replace("-", "/");
        contexto.fillText(rotulo, ponto.x - 10, altura - 10);
    });
}

// ---- Restaurar seleção se já houve check-in hoje ----
function restaurarSelecaoHoje(registros) {
    const hoje = registros.find((r) => r.data === chaveHoje());
    if (!hoje) {
        return;
    }

    botoesHumor.forEach((botao) => {
        const corresponde = Number(botao.dataset.humor) === hoje.humor;
        botao.classList.toggle("selecionado", corresponde);
        botao.setAttribute("aria-checked", String(corresponde));
    });
    campoNota.value = hoje.nota || "";
    humorSelecionado = hoje.humor;
    botaoSalvar.disabled = false;
}

// ---- Renderização geral ----
function renderizar() {
    const registros = carregarRegistros();
    renderizarEstatisticas(registros);
    renderizarHistorico(registros);
    renderizarGrafico(registros);
}

restaurarSelecaoHoje(carregarRegistros());
renderizar();
