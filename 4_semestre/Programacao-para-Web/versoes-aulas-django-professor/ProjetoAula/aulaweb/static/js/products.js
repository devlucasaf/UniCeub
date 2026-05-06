document.addEventListener("DOMContentLoaded", () => {
    const cards = document.querySelectorAll(".produto-card");

    cards.forEach(card => {
        const precoEl = card.querySelector(".produto-preco");
        if (precoEl) {
        const preco = parseFloat(precoEl.textContent.replace("R$", "").trim());
        if (preco < 50) {
            card.style.border = "2px solid #16a34a"; 
        }
        }
    });
});
