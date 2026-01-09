document.addEventListener('DOMContentLoaded', function() {
    updateYear();
    setupMobileMenu();
    highlightActiveLink();
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
    });
    
    document.addEventListener('click', function(e) {
        if (window.innerWidth <= 760 && navToggle.checked) {
        if (!menu.contains(e.target) && !hamburger.contains(e.target)) {
            navToggle.checked = false;
        }
        }
    });
    
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && navToggle.checked) {
        navToggle.checked = false;
        }
    });
}

function highlightActiveLink() {
    const currentPath = window.location.pathname;
    const menuLinks = document.querySelectorAll('.menu a');
    
    menuLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href && currentPath.includes(href.replace(/^\//, ''))) {
        link.classList.add('active');
        }
    });
}