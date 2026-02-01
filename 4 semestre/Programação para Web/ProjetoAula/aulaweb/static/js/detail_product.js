document.addEventListener('DOMContentLoaded', function() {
    setupProductPage();
});

function setupProductPage() {
    setupBackButton();
    formatPrice();
    setupImageError();
}

function setupBackButton() {
    const btnVoltar = document.querySelector('.btn-voltar');
    
    if (!btnVoltar) {
        return;
    }
    
    btnVoltar.addEventListener('click', function(e) {
        if (document.referrer === '') {
            e.preventDefault();
            window.location.href = '/produtos/';
        }
    });
}

function formatPrice() {
    const precoElement = document.querySelector('.produto-preco');
    
    if (!precoElement) {
        return;
    }
    
    let texto = precoElement.textContent.trim();
    
    texto = texto.replace('R$', '').trim();
    
    const numero = parseFloat(texto.replace(',', '.'));
    
    if (!isNaN(numero)) {
        const formatado = numero.toLocaleString('pt-BR', {
            style: 'currency',
            currency: 'BRL'
        });
        
        precoElement.textContent = formatado;
    }
}

function setupImageError() {
    const produtoImagem = document.querySelector('.produto-imagem');
    
    if (produtoImagem) {
        produtoImagem.addEventListener('error', function() {
            this.style.display = 'none';
            
            const placeholder = document.createElement('div');
            placeholder.className = 'imagem-placeholder';
            placeholder.innerHTML = '<span>Sem imagem</span>';
            placeholder.style.cssText = `
                width: 100%;
                height: 200px;
                background: #f3f4f6;
                border-radius: 12px;
                margin-bottom: 16px;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #6b7280;
                font-size: 1rem;
            `;
            
            this.parentNode.insertBefore(placeholder, this);
        });
    }
}

if (typeof window !== 'undefined') {
    window.DetailProduct = {
        setupProductPage,
        formatPrice
    };
}