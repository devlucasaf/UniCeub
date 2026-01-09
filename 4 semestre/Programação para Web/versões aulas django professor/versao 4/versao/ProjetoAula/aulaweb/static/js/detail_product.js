document.addEventListener('DOMContentLoaded', function() {
    initializeProductPage();
});

function initializeProductPage() {
    setupImageZoom();
    setupBuyButton();
    setupBackButton();
    setupSharing();
    formatProductPrice();
    setupFavoriteSystem();
    addProductMetadata();
    initializeRatings();
}

function setupImageZoom() {
    const produtoImagem = document.querySelector('.produto-imagem');
    
    if (!produtoImagem) return;
    
    const modal = document.createElement('div');
    modal.className = 'modal-imagem';
    modal.innerHTML = `
        <span class="fechar-modal">&times;</span>
        <img 
            src="${produtoImagem.src}" 
            alt="${produtoImagem.alt}"
        />
    `;
    
    document.body.appendChild(modal);
    
    const modalImg = modal.querySelector('img');
    const closeModal = modal.querySelector('.fechar-modal');
    
    produtoImagem.addEventListener('click', function() {
        modal.style.display = 'flex';
        document.body.style.overflow = 'hidden';
    });
    
    closeModal.addEventListener('click', function() {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    });
    
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            modal.style.display = 'none';
            document.body.style.overflow = 'auto';
        }
    });
    
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && modal.style.display === 'flex') {
            modal.style.display = 'none';
            document.body.style.overflow = 'auto';
        }
    });
}

function setupBuyButton() {
    const btnComprar = document.querySelector('.btn-comprar');
    
    if (!btnComprar) return;
    
    btnComprar.addEventListener('click', async function() {
        const produtoId = this.dataset.produtoId;
        const produtoNome = document.querySelector('.produto-nome').textContent;
        
        const originalText = this.innerHTML;
        this.innerHTML = '<span class="btn-icon">⏳</span> Adicionando...';
        this.disabled = true;
        
        try {
            await addToCart(produtoId);
            
            this.innerHTML = '<span class="btn-icon">✅</span> Adicionado!';
            this.style.background = 'linear-gradient(135deg, #059669 0%, #047857 100%)';
            
            showToast('Produto adicionado ao carrinho!', 'success');
            
            updateCartCounter();
            
            setTimeout(() => {
                this.innerHTML = originalText;
                this.disabled = false;
                this.style.background = 'linear-gradient(135deg, #10b981 0%, #059669 100%)';
            }, 2000);
            
        } catch (error) {
            this.innerHTML = '<span class="btn-icon">❌</span> Erro';
            this.style.background = 'linear-gradient(135deg, #ef4444 0%, #dc2626 100%)';
            
            showToast('Erro ao adicionar produto. Tente novamente.', 'error');
            
            setTimeout(() => {
                this.innerHTML = originalText;
                this.disabled = false;
                this.style.background = 'linear-gradient(135deg, #10b981 0%, #059669 100%)';
            }, 3000);
        }
    });
}

async function addToCart(produtoId) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (Math.random() > 0.1) {
                const cart = JSON.parse(localStorage.getItem('cart') || '[]');
                cart.push({
                    id: produtoId,
                    addedAt: new Date().toISOString()
                });
                localStorage.setItem('cart', JSON.stringify(cart));
                resolve();
            } else {
                reject(new Error('Falha na conexão'));
            }
        }, 800);
    });
}

function setupBackButton() {
    const btnVoltar = document.querySelector('.btn-voltar');
    
    if (!btnVoltar) return;
    
    btnVoltar.addEventListener('click', function(e) {
        const cartUpdated = localStorage.getItem('cartUpdated');
        
        if (cartUpdated === 'true') {
            if (!confirm('Você adicionou itens ao carrinho. Tem certeza que deseja sair?')) {
                e.preventDefault();
                return;
            }
        }
        
        if (document.referrer === '') {
            e.preventDefault();
            window.location.href = '/produtos/';
        }
    });
}

function formatProductPrice() {
    const precoElement = document.querySelector('.preco-valor');
    
    if (!precoElement) return;
    
    let texto = precoElement.textContent.trim();
    
    texto = texto.replace('R$', '').trim();
    
    const numero = parseFloat(texto.replace(',', '.'));
    
    if (!isNaN(numero)) {
        const formatado = numero.toLocaleString('pt-BR', {
            style: 'currency',
            currency: 'BRL',
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
        
        precoElement.textContent = formatado;
    }
}

function setupSharing() {
    const shareData = {
        title: document.querySelector('.produto-nome')?.textContent || 'Produto',
        text: document.querySelector('.produto-descricao')?.textContent || '',
        url: window.location.href
    };
    
    if (navigator.share) {
        addShareButton(shareData);
    }
}

function addShareButton(shareData) {
    const shareBtn = document.createElement('button');
    shareBtn.className = 'btn-compartilhar';
    shareBtn.innerHTML = '<span class="btn-icon">📤</span> Compartilhar';
    
    shareBtn.addEventListener('click', async () => {
        try {
            await navigator.share(shareData);
            showToast('Compartilhado com sucesso!', 'success');
        } catch (err) {
            console.log('Erro ao compartilhar:', err);
        }
    });
    
    const acoes = document.querySelector('.produto-acoes');
    if (acoes) {
        acoes.appendChild(shareBtn);
    }
}

function setupFavoriteSystem() {
    const produtoId = document.querySelector('.btn-comprar')?.dataset.produtoId;
    
    if (!produtoId) return;
    
    const favoriteBtn = document.createElement('button');
    favoriteBtn.className = 'btn-favorito';
    favoriteBtn.innerHTML = '<span class="btn-icon">🤍</span> Favoritar';
    favoriteBtn.dataset.produtoId = produtoId;
    
    const favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
    if (favorites.includes(produtoId)) {
        favoriteBtn.innerHTML = '<span class="btn-icon">❤️</span> Favorito';
        favoriteBtn.classList.add('favorited');
    }
    
    favoriteBtn.addEventListener('click', function() {
        const produtoId = this.dataset.produtoId;
        const favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
        
        if (this.classList.contains('favorited')) {
            const index = favorites.indexOf(produtoId);
            if (index > -1) {
                favorites.splice(index, 1);
            }
            this.innerHTML = '<span class="btn-icon">🤍</span> Favoritar';
            this.classList.remove('favorited');
            showToast('Removido dos favoritos', 'info');
        } else {
            favorites.push(produtoId);
            this.innerHTML = '<span class="btn-icon">❤️</span> Favorito';
            this.classList.add('favorited');
            showToast('Adicionado aos favoritos!', 'success');
        }
        
        localStorage.setItem('favorites', JSON.stringify(favorites));
    });
    
    const acoes = document.querySelector('.produto-acoes');
    if (acoes) {
        acoes.appendChild(favoriteBtn);
    }
}

function addProductMetadata() {
    const produtoNome = document.querySelector('.produto-nome')?.textContent;
    const produtoPreco = document.querySelector('.preco-valor')?.textContent;
    const produtoImagem = document.querySelector('.produto-imagem')?.src;
    
    if (produtoNome && produtoPreco) {
        const script = document.createElement('script');
        script.type = 'application/ld+json';
        script.textContent = JSON.stringify({
            "@context": "https://schema.org/",
            "@type": "Product",
            "name": produtoNome,
            "description": document.querySelector('.produto-descricao')?.textContent || '',
            "image": produtoImagem || '',
            "offers": {
                "@type": "Offer",
                "price": produtoPreco.replace('R$', '').replace(',', '.').trim(),
                "priceCurrency": "BRL",
                "availability": document.querySelector('.produto-estoque') ? 
                    "https://schema.org/InStock" : "https://schema.org/OutOfStock"
            }
        });
        
        document.head.appendChild(script);
    }
}

function initializeRatings() {
    const ratingsContainer = document.querySelector('.produto-avaliacoes');
    
    if (ratingsContainer) {
    }
}

function updateCartCounter() {
    const cartCounter = document.querySelector('.cart-counter');
    if (cartCounter) {
        const cart = JSON.parse(localStorage.getItem('cart') || '[]');
        cartCounter.textContent = cart.length;
        cartCounter.style.display = cart.length > 0 ? 'flex' : 'none';
    }
}

function showToast(message, type = 'info') {
    const existingToast = document.querySelector('.toast-message');
    if (existingToast) {
        existingToast.remove();
    }
    
    const toast = document.createElement('div');
    toast.className = `toast-message toast-${type}`;
    toast.textContent = message;
    toast.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 12px 20px;
        background: ${type === 'success' ? '#10b981' : type === 'error' ? '#ef4444' : '#3b82f6'};
        color: white;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        z-index: 1000;
        animation: slideInRight 0.3s ease;
    `;
    
    document.body.appendChild(toast);
    
    setTimeout(() => {
        toast.style.animation = 'slideOutRight 0.3s ease forwards';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

const style = document.createElement('style');
style.textContent = `
    @keyframes slideInRight {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    @keyframes slideOutRight {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);

function setupPrint() {
    const printBtn = document.createElement('button');
    printBtn.className = 'btn-imprimir';
    printBtn.innerHTML = '<span class="btn-icon">🖨️</span> Imprimir';
    
    printBtn.addEventListener('click', () => {
        window.print();
    });
    
    const acoes = document.querySelector('.produto-acoes');
    if (acoes) {
        acoes.appendChild(printBtn);
    }
}

const printStyle = document.createElement('style');
printStyle.media = 'print';
printStyle.textContent = `
    header, footer, .produto-acoes, .btn-voltar {
        display: none !important;
    }
    .container {
        box-shadow: none !important;
        max-width: 100% !important;
    }
`;
document.head.appendChild(printStyle);

if (typeof window !== 'undefined') {
    window.ProductDetail = {
        addToCart,
        showToast,
        updateCartCounter
    };
}
