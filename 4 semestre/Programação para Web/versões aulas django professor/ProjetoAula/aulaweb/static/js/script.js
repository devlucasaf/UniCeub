document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('year').textContent = new Date().getFullYear();
});

const navToggle = document.getElementById('nav-toggle');
if (navToggle) {
    navToggle.addEventListener('change', () => {
        if (navToggle.checked) {
        console.log("Menu de navegação aberto!");
        } 
        else {
        console.log("Menu de navegação fechado!");
        }
    });
}
