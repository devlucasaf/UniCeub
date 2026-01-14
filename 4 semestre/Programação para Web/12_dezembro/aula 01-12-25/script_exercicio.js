// Código com problemas de usabilidade: dependente de mouse, sem alternativa teclado
function openProduct(id) {
    alert("Abrindo produto " + id);
}

// Simula popup automático (invasivo)
setTimeout(function () {
    var p = document.createElement('div');
    p.id = 'promo';
    p.style.position = 'fixed';
    p.style.right = '10px';
    p.style.bottom = '10px';
    p.style.background = '#ff0';
    p.style.padding = '10px';
    p.innerHTML = '<p>Oferta! <a href="#">Ganhe desconto</a></p>';
    document.body.appendChild(p);
}, 2000);
