document.addEventListener("DOMContentLoaded", () => {
    const btnVoltar = document.querySelector(".btn-voltar");
    if (btnVoltar) {
        btnVoltar.addEventListener("click", () => {
        console.log("Usuário voltou para a página anterior.");
        });
    }
});
