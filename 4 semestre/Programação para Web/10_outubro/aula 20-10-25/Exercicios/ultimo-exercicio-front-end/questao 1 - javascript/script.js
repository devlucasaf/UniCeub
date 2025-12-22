// Aguarda o carregamento completo do DOM
document.addEventListener('DOMContentLoaded', function() {
    // Obtém referências aos elementos
    const paragrafo = document.getElementById('texto-paragrafo');
    const botaoAlterar = document.getElementById('botao-alterar');
    const botaoResetar = document.getElementById('botao-resetar');
    
    // Texto original do parágrafo
    const textoOriginal = paragrafo.textContent;
    
    // Textos alternativos
    const textosAlternativos = [
        "O texto foi alterado com sucesso usando JavaScript puro!",
        "Você clicou novamente e o texto mudou outra vez!",
        "JavaScript é uma linguagem poderosa para a web.",
        "Mais uma alteração no texto. Continue clicando!",
        "Esta é a última mensagem alternada. Agora voltará ao início."
    ];
    
    let indiceTexto = 0;
    
    // Função para alterar o texto do parágrafo
    function alterarTexto() {
        paragrafo.textContent = textosAlternativos[indiceTexto];
        indiceTexto = (indiceTexto + 1) % textosAlternativos.length;
        
        // Altera a cor da borda para dar feedback visual
        paragrafo.style.borderLeftColor = getRandomColor();
    }
    
    // Função para resetar o texto ao original
    function resetarTexto() {
        paragrafo.textContent = textoOriginal;
        paragrafo.style.borderLeftColor = '#4CAF50';
        indiceTexto = 0;
    }
    
    // Função para gerar cores aleatórias
    function getRandomColor() {
        const colors = ['#4CAF50', '#2196F3', '#FF9800', '#9C27B0', '#F44336'];
        return colors[Math.floor(Math.random() * colors.length)];
    }
    
    // Adiciona os eventos de clique aos botões
    botaoAlterar.addEventListener('click', alterarTexto);
    botaoResetar.addEventListener('click', resetarTexto);
});
