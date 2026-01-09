document.addEventListener('DOMContentLoaded', function() {
    updateYear();
    
    setupMobileMenu();
    
    addSkipToContentLink();
    
    highlightActiveLink();
    
    enhanceHamburgerAccessibility();
});

function updateYear() {
    const yearElement = document.getElementById('year');
    if (yearElement) {
        yearElement.textContent = new Date().getFullYear();
    }
}

function setupMobileMenu() {
    const hamburger = document.querySelector('.hamburger');
    const navToggle = document.getElementById('nav-toggle');
    const menu = document.querySelector('.menu');
    
    if (!hamburger || !navToggle || !menu) return;
    
    hamburger.addEventListener('click', function(e) {
        e.preventDefault();
        navToggle.checked = !navToggle.checked;
        
        this.classList.toggle('active');
        
        if (navToggle.checked) {
        menu.style.display = 'flex';

        setTimeout(() => {
            const firstLink = menu.querySelector('a');
            if (firstLink) firstLink.focus();
        }, 100);
        } else {
        menu.style.display = 'none';
        }
    });
    
    document.addEventListener('click', function(e) {
        if (window.innerWidth <= 768 && navToggle.checked) {
        if (!menu.contains(e.target) && !hamburger.contains(e.target)) {
            navToggle.checked = false;
            hamburger.classList.remove('active');
            menu.style.display = 'none';
        }
        }
    });
    
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && navToggle.checked) {
        navToggle.checked = false;
        hamburger.classList.remove('active');
        menu.style.display = 'none';
        hamburger.focus();
        }
    });
}

function addSkipToContentLink() {
    const skipLink = document.createElement('a');
    skipLink.href = '#main-content';
    skipLink.className = 'skip-to-content';
    skipLink.textContent = 'Pular para o conteúdo';
        const mainElement = document.querySelector('main');
    if (mainElement) {
        mainElement.id = 'main-content';
        document.body.insertBefore(skipLink, document.body.firstChild);
    }
}

function highlightActiveLink() {
    const currentPath = window.location.pathname;
    const menuLinks = document.querySelectorAll('.menu a');
    
    menuLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href && currentPath.includes(href.replace(/^\//, ''))) {
        link.classList.add('active');
        
        link.setAttribute('aria-current', 'page');
        }
    });
}

function enhanceHamburgerAccessibility() {
    const hamburger = document.querySelector('.hamburger');
    const navToggle = document.getElementById('nav-toggle');
    
    if (!hamburger || !navToggle) return;
    
    function updateHamburgerLabel() {
        const isExpanded = navToggle.checked;
        hamburger.setAttribute('aria-expanded', isExpanded);
        hamburger.setAttribute('aria-label', isExpanded ? 'Fechar menu de navegação' : 'Abrir menu de navegação');
    }
    
    updateHamburgerLabel();
    
    navToggle.addEventListener('change', updateHamburgerLabel);
    
    hamburger.addEventListener('keydown', function(e) {
        if (e.key === 'Enter' || e.key === ' ') {
        e.preventDefault();
        this.click();
        }
    });
}

function handleResize() {
    const navToggle = document.getElementById('nav-toggle');
    const menu = document.querySelector('.menu');
    const hamburger = document.querySelector('.hamburger');
    
    if (window.innerWidth > 768) {
        if (menu) menu.style.display = 'flex';
        if (navToggle) navToggle.checked = false;
        if (hamburger) hamburger.classList.remove('active');
    } 
    else {
        if (menu && navToggle) {
        menu.style.display = navToggle.checked ? 'flex' : 'none';
        }
    }
}

window.addEventListener('resize', debounce(handleResize, 250));

function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
        clearTimeout(timeout);
        func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

function addPageTransition() {
    document.body.style.opacity = '0';
    document.body.style.transition = 'opacity 0.3s ease';
    
    window.addEventListener('load', function() {
        document.body.style.opacity = '1';
    });
}
