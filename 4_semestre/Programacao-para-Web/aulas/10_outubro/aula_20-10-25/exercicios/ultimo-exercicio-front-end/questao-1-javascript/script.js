document.addEventListener('DOMContentLoaded', function() {
    const paragrafo = document.getElementById('texto-paragrafo');
    const botaoAlterar = document.getElementById('botao-alterar');
    const botaoResetar = document.getElementById('botao-resetar');
    
    const textoOriginal = paragrafo.textContent;
    
    const textosAlternativos = [
        "O texto foi alterado com sucesso usando JavaScript puro!",
        "Você clicou novamente e o texto mudou outra vez!",
        "JavaScript é uma linguagem poderosa para a web.",
        "Mais uma alteração no texto. Continue clicando!",
        "Esta é a última mensagem alternada. Agora voltará ao início."
    ];
    
    let indiceTexto = 0;
    
    function alterarTexto() {
        paragrafo.textContent = textosAlternativos[indiceTexto];
        indiceTexto = (indiceTexto + 1) % textosAlternativos.length;
        
        paragrafo.style.borderLeftColor = getRandomColor();
    }
    
    function resetarTexto() {
        paragrafo.textContent = textoOriginal;
        paragrafo.style.borderLeftColor = '#4CAF50';
        indiceTexto = 0;
    }
    
    function getRandomColor() {
        const colors = ['#4CAF50', '#2196F3', '#FF9800', '#9C27B0', '#F44336'];
        return colors[Math.floor(Math.random() * colors.length)];
    }
    
    botaoAlterar.addEventListener('click', alterarTexto);
    botaoResetar.addEventListener('click', resetarTexto);
});
