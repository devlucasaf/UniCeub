document.addEventListener("DOMContentLoaded", function() {
    console.log("Sistema de Vendas carregado com sucesso!");
});

const btnAlert = document.getElementById("btn-alert");
if (btnAlert) {
    btnAlert.addEventListener("click", () => {
        alert("Você clicou no botão de alerta!");
    });
}
