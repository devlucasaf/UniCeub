const lugares = {
    "padaria": { 
        nome: "Padaria do Bairro", 
        distancia: "80 metros à frente, depois vire à direita" 
    },
    "farmácia": { 
        nome: "Farmácia Popular", 
        distancia: "150 metros, siga em frente e atravesse a rua" 
    },
    "mercado": { 
        nome: "Supermercado Central", 
        distancia: "200 metros, vire à esquerda no semáforo" 
    },
    "banco": { 
        nome: "Banco do Brasil", 
        distancia: "300 metros, continue reto até a praça" 
    },
    "praça": { 
        nome: "Praça da Matriz", 
        distancia: "120 metros, à direita após a igreja" 
    }
};

let navegacaoAtiva = false;
let destinoAtual = "";
let localAtual = "Em casa (sala)";
let navInterval = null;

const localAtualSpan = document.getElementById("localAtual");
const destinoInfoSpan = document.getElementById("destinoInfo");
const feedbackDiv = document.getElementById("feedbackArea");

function falar(texto, urgente = false) {
    if (!window.speechSynthesis) {
        console.warn("Web Speech API não suportada.");
        adicionarFeedback("(sem áudio) " + texto);
        return;
    }

    const utterance = new SpeechSynthesisUtterance(texto);
    utterance.lang = "pt-BR";
    utterance.rate = 0.9;
    utterance.pitch = 1.0;
    if (urgente) {
        utterance.rate = 1.0;
    }

    window.speechSynthesis.cancel();
    window.speechSynthesis.speak(utterance);
    adicionarFeedback(`Falando: "${texto}"`);
}

function vibrar(padrao = [100, 50, 100]) {
    const container = document.querySelector(".container");
    if (container) {
        container.classList.add("vibrar-simulado");
        setTimeout(() => container.classList.remove("vibrar-simulado"), 500);
    }

    if (window.navigator && window.navigator.vibrate) {
        window.navigator.vibrate(padrao);
    } 
    
    else {
        adicionarFeedback("(simulação tátil - dispositivo sem vibração)");
    }
}

function adicionarFeedback(msg) {
    const p = document.createElement("p");
    p.textContent = `> ${new Date().toLocaleTimeString()} - ${msg}`;
    feedbackDiv.appendChild(p);
    feedbackDiv.scrollTop = feedbackDiv.scrollHeight;
    while (feedbackDiv.children.length > 15) feedbackDiv.removeChild(feedbackDiv.firstChild);
}

function atualizarInterface() {
    localAtualSpan.textContent = `Local atual: ${localAtual}`;
    if (navegacaoAtiva && destinoAtual) {
        destinoInfoSpan.textContent = `Navegando para: ${destinoAtual}`;
    } 
    
    else {
        destinoInfoSpan.textContent = `Destino: nenhum`;
    }
}

function iniciarNavegacao(destinoKey) {
    const destinoLower = destinoKey.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    let lugar = null;
    let chaveEncontrada = "";

    for (const key in lugares) {
        if (destinoLower.includes(key) || key.includes(destinoLower)) {
            lugar = lugares[key];
            chaveEncontrada = key;
            break;
        }
    }

    if (!lugar && destinoKey.trim() !== "") {
        destinoAtual = destinoKey;
        lugar = { nome: destinoKey, distancia: "direção desconhecida. Peça ajuda ou use mapa sonoro." };
    } 
    
    else if (!lugar) {
        falar("Destino não reconhecido. Diga o nome de um lugar como padaria, farmácia, mercado.");
        adicionarFeedback("Destino inválido.");
        return;
    } 
    
    else {
        destinoAtual = lugar.nome;
    }

    navegacaoAtiva = true;
    const instrucao = `Iniciando navegação para ${lugar.nome}. ${lugar.distancia}. Atenção aos obstáculos.`;
    falar(instrucao);
    vibrar([200]);
    adicionarFeedback(`Navegação ativa: ${lugar.nome} - ${lugar.distancia}`);

    if (navInterval) {
        clearInterval(navInterval);
    }
    
    let passos = 0;
    navInterval = setInterval(() => {
        if (!navegacaoAtiva) {
            if (navInterval) {
                clearInterval(navInterval);
            }
            return;
        }
        passos++;
        if (passos === 3) {
            falar("Continue seguindo em frente, você está no caminho certo.");
            vibrar([50]);
        } 
        
        else if (passos === 6) {
            falar("Atenção: próximo à travessia. Verifique o piso tátil.");
            vibrar([100, 100, 100]);
        } 
        
        else if (passos > 8) {
            falar(`Você chegou próximo ao destino: ${lugar.nome}. Navegação concluída.`);
            vibrar([300]);
            pararNavegacao();
        }
    }, 7000);

    atualizarInterface();
}

function pararNavegacao() {
    if (navInterval) {
        clearInterval(navInterval);
    }

    navegacaoAtiva = false;
    const msg = "Navegação interrompida.";
    falar(msg);
    vibrar([100]);
    adicionarFeedback("Navegação cancelada.");
    destinoAtual = "";
    atualizarInterface();
}

function ondeEstou() {
    const msg = `Você está ${localAtual}. Utilize os comandos para navegar.`;
    falar(msg);
    vibrar([80]);
    adicionarFeedback(`${msg}`);
}

function simularObstaculo() {
    if (!navegacaoAtiva) {
        falar("Nenhuma navegação ativa. Inicie um trajeto primeiro.");
        vibrar([200]);
        return;
    }
    const alerta = "Atenção! Obstáculo detectado à frente. Pare e desvie pela esquerda.";
    falar(alerta, true);
    vibrar([300, 200, 300]);
    adicionarFeedback(`${alerta}`);
    falar("Após desviar, continue seguindo a rota original.");
}

let recognition = null;
if ('webkitSpeechRecognition' in window || 'SpeechRecognition' in window) {
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    recognition = new SpeechRecognition();
    recognition.lang = 'pt-BR';
    recognition.continuous = false;
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;
} 

else {
    adicionarFeedback("Reconhecimento de voz não suportado neste navegador. Use os botões.");
}

function iniciarReconhecimentoVoz() {
    if (!recognition) {
        falar("Reconhecimento de voz não disponível.");
        return;
    }
    falar("Diga um comando.");
    recognition.start();
    adicionarFeedback("Ouvindo...");
}

if (recognition) {
    recognition.onresult = (event) => {
        const comando = event.results[0][0].transcript.toLowerCase();
        adicionarFeedback(`Comando reconhecido: "${comando}"`);
        processarComandoVoz(comando);
    };
    recognition.onerror = (event) => {
        adicionarFeedback(`Erro no reconhecimento: ${event.error}`);
        falar("Não entendi, tente novamente.");
    };
    recognition.onend = () => {
        adicionarFeedback("🎤 Reconhecimento finalizado.");
    };
}

function processarComandoVoz(comando) {
    if (comando.includes("onde estou")) {
        ondeEstou();
    } 
    
    else if (comando.includes("navegar até") || comando.includes("ir para")) {
        let destino = comando.replace(/navegar até|ir para/g, '').trim();
        if (destino === "") {
            falar("Diga o nome do destino.");
        } 
        
        else {
            iniciarNavegacao(destino);
        }
    } 
    
    else if (comando.includes("parar navegação") || comando.includes("cancelar")) {
        pararNavegacao();
    } 
    
    else if (comando.includes("obstáculo")) {
        simularObstaculo();
    } 
    
    else {
        falar("Comando não reconhecido. Diga: onde estou, navegar até (lugar), parar navegação ou obstáculo.");
    }
}

document.getElementById("btnOndeEstou").addEventListener("click", ondeEstou);
document.getElementById("btnPadaria").addEventListener("click", () => iniciarNavegacao("padaria"));
document.getElementById("btnFarmacia").addEventListener("click", () => iniciarNavegacao("farmácia"));
document.getElementById("btnParar").addEventListener("click", pararNavegacao);
document.getElementById("btnObstaculo").addEventListener("click", simularObstaculo);

const destinoTexto = document.getElementById("destinoTexto");
const btnIr = document.getElementById("btnIrDestino");
btnIr.addEventListener("click", () => {
    const texto = destinoTexto.value.trim();
    if (texto === "") {
        falar("Digite um destino no campo.");
        return;
    }
    iniciarNavegacao(texto);
    destinoTexto.value = "";
});

destinoTexto.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        btnIr.click();
    }
});

const btnVoz = document.createElement("button");
btnVoz.textContent = "Comando de voz";
btnVoz.style.width = "100%";
btnVoz.style.marginTop = "10px";
btnVoz.addEventListener("click", iniciarReconhecimentoVoz);
document.querySelector(".btn-group").appendChild(btnVoz);

setTimeout(() => {
    falar("Bem-vindo, Ricardo. Sistema de navegação ativo. Use botões ou comandos de voz.");
}, 500);
adicionarFeedback("Sistema carregado. Clique em 'Comando de voz' e diga, por exemplo: 'Navegar até padaria'");