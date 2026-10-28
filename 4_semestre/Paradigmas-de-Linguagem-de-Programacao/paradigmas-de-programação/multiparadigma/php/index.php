<?php

require_once __DIR__ . '/config.php';
require_once __DIR__ . '/helpers.php';
require_once __DIR__ . '/src/Produto.php';
require_once __DIR__ . '/src/Pedido.php';
require_once __DIR__ . '/src/PedidoService.php';

$produtosDisponiveis = [
    new Produto("Notebook", 3500),
    new Produto("Mouse", 150),
    new Produto("Teclado", 250),
    new Produto("Monitor", 1200),
];

$filtrarCaros = function($produto) {
    return $produto->getPreco() > 200;
};

$produtosCaros = array_filter(
    $produtosDisponiveis,
    $filtrarCaros
);

$nomesProdutos = array_map(function($produto) {
    return $produto->getNome();
}, $produtosCaros);

logMensagem("Produtos caros disponíveis:");

foreach ($nomesProdutos as $nome) {
    logMensagem("- $nome");
}

$pedido = new Pedido();

foreach ($produtosCaros as $produto) {
    $pedido->adicionarProduto($produto);
}

$service = new PedidoService();

$total = $service->gerarResumo($pedido);

$totalComDesconto = $service->aplicarDesconto($total, 10);

logMensagem("Total com desconto: " .formatarPreco($totalComDesconto));

$soma = array_reduce(
    $produtosCaros,
    function($acumulador, $produto) {
        return $acumulador + $produto->getPreco();
    },
    0
);

logMensagem("Total recalculado com reduce: " .formatarPreco($soma));

aoFinalizarPedido(function() {
    logMensagem("Pedido finalizado com sucesso!");
});