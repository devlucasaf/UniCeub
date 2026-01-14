const paragrafo = document.getElementById('texto-magico');
const botao = document.getElementById('btn-mudar');

botao.addEventListener('click', function() {
    paragrafo.textContent = 'A mágica aconteceu!';
    paragrafo.style.color = 'red';
    paragrafo.style.fontSize = '24px';
});
