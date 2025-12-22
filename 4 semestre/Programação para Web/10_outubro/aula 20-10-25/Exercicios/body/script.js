// Atualizar dinamicamente o indicador de breakpoint
function updateBreakpointInfo() {
    const infoElement = document.querySelector('.breakpoint-info');
    const width = window.innerWidth;
    
    if (width >= 1024) {
        infoElement.innerHTML = '<span class="color-indicator desktop-color"></span> Modo Desktop: 3 colunas | Redimensione a janela do navegador para ver o layout se adaptando.';
    } else if (width >= 600) {
        infoElement.innerHTML = '<span class="color-indicator tablet-color"></span> Modo Tablet: 2 colunas | Redimensione a janela do navegador para ver o layout se adaptando.';
    } else {
        infoElement.innerHTML = '<span class="color-indicator mobile-color"></span> Modo Celular: 1 coluna | Redimensione a janela do navegador para ver o layout se adaptando.';
    }
}

// Atualizar quando a janela for redimensionada
window.addEventListener('resize', updateBreakpointInfo);

// Executar uma vez ao carregar
document.addEventListener('DOMContentLoaded', updateBreakpointInfo);