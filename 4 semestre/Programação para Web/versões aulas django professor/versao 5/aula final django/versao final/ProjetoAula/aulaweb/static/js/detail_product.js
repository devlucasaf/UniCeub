document.addEventListener('DOMContentLoaded', function() {
    const btnVoltar = document.querySelector('.btn-voltar');
    
    if (btnVoltar) {
        const produtoImagem = document.querySelector('.produto-imagem');
        if (produtoImagem) {

            produtoImagem.style.opacity = '0';
            produtoImagem.style.transition = 'opacity 0.5s ease';
            
            if (produtoImagem.complete) {
                produtoImagem.style.opacity = '1';
            } else {
                produtoImagem.addEventListener('load', function() {
                    produtoImagem.style.opacity = '1';
                });
            }
        }
        
        const produtoPreco = document.querySelector('.produto-preco');
        if (produtoPreco) {
            produtoPreco.title = 'Preço do produto';
        }
    }
    
    function formatarPreco() {
        const precoElemento = document.querySelector('.produto-preco');
        if (precoElemento) {
            const texto = precoElemento.textContent;

            const numero = texto.replace('R$ ', '').trim();

            const formatado = parseFloat(numero).toFixed(2);
            precoElemento.textContent = `R$ ${formatado.replace('.', ',')}`;
        }
    }
    
        document.body.classList.add('pagina-detalhes-produto');

        console.log('Página de detalhes do produto carregada');
}); 
