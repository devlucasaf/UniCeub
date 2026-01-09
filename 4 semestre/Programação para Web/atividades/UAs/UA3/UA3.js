document.addEventListener('DOMContentLoaded', function() {
    setupCoresPage();
});

function setupCoresPage() {
    highlightCurrentColor();
    addColorInteraction();
    setupAnimations();
}

function highlightCurrentColor() {
    const olItems = document.querySelectorAll('ol li');
    
    olItems.forEach(item => {
        const text = item.textContent.toLowerCase();
        const colorMap = {
            'vermelho': '#ff0000',
            'azul': '#0000ff',
            'amarelo': '#ffff00',
            'verde': '#00ff00',
            'laranja': '#ffa500',
            'roxo': '#800080',
            'oliva': '#808000',
            'turquesa': '#40e0d0',
            'celeste': '#87ceeb',
            'violeta': '#ee82ee',
            'rosa': '#ffc0cb'
        };
        
        if (colorMap[text]) {
            item.style.borderLeft = `3px solid ${colorMap[text]}`;
        }
    });
}

function addColorInteraction() {
    const olItems = document.querySelectorAll('ol li');
    
    olItems.forEach(item => {
        item.addEventListener('click', function() {
            const colorName = this.textContent;
            const colorCode = getColorCode(colorName);
            
            const colorInfo = document.createElement('div');
            colorInfo.className = 'color-info';
            colorInfo.innerHTML = `
                <div class="color-preview" style="background: ${colorCode};"></div>
                <div class="color-details">
                    <strong>${colorName}</strong><br>
                    <small>${colorCode}</small>
                </div>
            `;
            
            colorInfo.style.cssText = `
                position: fixed;
                top: 20px;
                right: 20px;
                background: #0f2b33;
                padding: 10px;
                border-radius: 8px;
                border: 2px solid #24d0b8;
                z-index: 1000;
                display: flex;
                align-items: center;
                gap: 10px;
                animation: fadeIn 0.3s ease;
            `;
            
            const colorPreview = colorInfo.querySelector('.color-preview');
            colorPreview.style.cssText = `
                width: 40px;
                height: 40px;
                border-radius: 50%;
                border: 2px solid white;
            `;
            
            document.body.appendChild(colorInfo);
            
            setTimeout(() => {
                colorInfo.style.animation = 'fadeOut 0.3s ease forwards';
                setTimeout(() => colorInfo.remove(), 300);
            }, 2000);
        });
    });
}

function getColorCode(colorName) {
    const colors = {
        'vermelho': '#ff0000',
        'azul': '#0000ff',
        'amarelo': '#ffff00',
        'verde': '#00ff00',
        'laranja': '#ffa500',
        'roxo': '#800080',
        'oliva': '#808000',
        'turquesa': '#40e0d0',
        'celeste': '#87ceeb',
        'violeta': '#ee82ee',
        'rosa': '#ffc0cb'
    };
    
    return colors[colorName.toLowerCase()] || '#24d0b8';
}

function setupAnimations() {
    const style = document.createElement('style');
    style.textContent = `
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        @keyframes fadeOut {
            from { opacity: 1; transform: translateY(0); }
            to { opacity: 0; transform: translateY(-10px); }
        }
        @keyframes pulse {
            0%, 100% { transform: scale(1); }
            50% { transform: scale(1.05); }
        }
    `;
    document.head.appendChild(style);
    
    const sections = document.querySelectorAll('li');
    sections.forEach((section, index) => {
        section.style.animationDelay = `${index * 0.1}s`;
        section.style.animation = `fadeIn 0.5s ease forwards`;
        section.style.opacity = '0';
    });
}

function setupPrint() {
    const printBtn = document.createElement('button');
    printBtn.textContent = '🖨️ Imprimir';
    printBtn.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #24d0b8;
        color: #071826;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        font-weight: bold;
        z-index: 100;
    `;
    
    printBtn.addEventListener('click', function() {
        window.print();
    });
    
    document.body.appendChild(printBtn);
}

setupPrint();

if (typeof window !== 'undefined') {
    window.CoresPage = {
        highlightCurrentColor,
        getColorCode
    };
}
