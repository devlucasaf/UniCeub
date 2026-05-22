<?php

// Autoloader simples para carregar as classes do mesmo diretório
spl_autoload_register(function ($class) {
    $file = __DIR__ . '/' . $class . '.php';
    if (file_exists($file)) {
        require_once $file;
    }
});

// Demonstração do fluxo original
$usuario = new Usuario(1, "Lucas", "lucas@email.com");

$produto1 = new Produto(1, "Notebook", 3000);
$produto2 = new Produto(2, "Mouse", 100);
$produto3 = new Produto(3, "Teclado", 200);

$item1 = new ItemPedido($produto1, 1);
$item2 = new ItemPedido($produto2, 2);
$item3 = new ItemPedido($produto3, 1);

$pedido = new Pedido(1, $usuario);
$pedido->adicionarItem($item1);
$pedido->adicionarItem($item2);
$pedido->adicionarItem($item3);

$service = new PedidoService();

$resumo = $service->gerarResumo($pedido);

$pagamento = new PagamentoCartao($pedido->calcularTotal(), "123456789");

$service->finalizarPedido($pedido, $pagamento);

$relatorio = new Relatorio();
$relatorio->imprimir($resumo);