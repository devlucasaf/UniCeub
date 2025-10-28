// Garante que o código só será executado após o carregamento completo da página

$(document).ready(function() {
    // Adiciona um evento de clique na classe 'produto-card'
    $('.produto-card').on('click', function() {
        // Obtém o nome do produto clicado
        var nomeProduto = $(this).find('.card-title').text();
        // Exibe um alerta com o nome do produto
        alert('Você clicou no produto: ' + nomeProduto);
    });
});
