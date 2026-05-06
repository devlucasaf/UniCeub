document.addEventListener('DOMContentLoaded', function() {
    updateYear();
    setupMobileMenu();
    addSkipLink();
    highlightActiveLink();
    enhanceHamburger();
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
    
    if (!hamburger || !navToggle || !menu) {
        return;
    }
    
    hamburger.addEventListener('click', function(e) {
        e.preventDefault();
        navToggle.checked = !navToggle.checked;
        this.classList.toggle('active');
        
        if (navToggle.checked) {
        menu.style.display = 'flex';
        } else {
        menu.style.display = 'none';
        }
    });
    
    document.addEventListener('click', function(e) {
        if (window.innerWidth <= 760 && navToggle.checked) {
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
        }
    });
}

function addSkipLink() {
    const skipLink = document.createElement('a');
    skipLink.href = '#main-content';
    skipLink.className = 'skip-link';
    skipLink.textContent = 'Pular para conteúdo';
    
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

function enhanceHamburger() {
    const hamburger = document.querySelector('.hamburger');
    const navToggle = document.getElementById('nav-toggle');
    
    if (!hamburger || !navToggle) {
        return;
    }
    
    function updateLabel() {
        const isExpanded = navToggle.checked;
        hamburger.setAttribute('aria-expanded', isExpanded);
        hamburger.setAttribute('aria-label', isExpanded ? 'Fechar menu' : 'Abrir menu');
    }
    
    updateLabel();
    
    navToggle.addEventListener('change', updateLabel);
    
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
    
    if (window.innerWidth > 760) {
        if (menu) {
            menu.style.display = 'flex';
        }
        if (navToggle) {
            navToggle.checked = false;
        }
        if (hamburger) {
            hamburger.classList.remove('active');
        }
    } else {
        if (menu && navToggle) {
            menu.style.display = navToggle.checked ? 'flex' : 'none';
        }
    }
}

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

window.addEventListener('resize', debounce(handleResize, 200));

if (typeof window !== 'undefined') {
    window.BaseLayout = {
        updateYear,
        setupMobileMenu,
        highlightActiveLink
    };
}
