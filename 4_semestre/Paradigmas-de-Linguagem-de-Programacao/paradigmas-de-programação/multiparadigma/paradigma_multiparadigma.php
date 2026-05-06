<?php

date_default_timezone_set('America/Sao_Paulo');

function logMensagem($mensagem) {
    echo "[" . date('Y-m-d H:i:s') . "] $mensagem\n";
}

function formatarPreco($valor) {
    return "R$ " . number_format($valor, 2, ',', '.');
}

class Produto {
    private $nome;
    private $preco;

    public function __construct($nome, $preco) {
        $this->nome = $nome;
        $this->preco = $preco;
    }

    public function getNome() {
        return $this->nome;
    }

    public function getPreco() {
        return $this->preco;
    }
}

class Pedido {
    private $produtos = [];

    public function adicionarProduto(Produto $produto) {
        $this->produtos[] = $produto;
    }

    public function listarProdutos() {
        return $this->produtos;
    }

    public function calcularTotal() {
        $total = 0;
        foreach ($this->produtos as $produto) {
            $total += $produto->getPreco();
        }
        return $total;
    }
}

class PedidoService {

    public function aplicarDesconto($total, $percentual) {
        return $total - ($total * ($percentual / 100));
    }

    public function gerarResumo(Pedido $pedido) {
        $produtos = $pedido->listarProdutos();

        logMensagem("Resumo do Pedido:");

        foreach ($produtos as $produto) {
            logMensagem("- " . $produto->getNome() . " | " . formatarPreco($produto->getPreco()));
        }

        $total = $pedido->calcularTotal();
        logMensagem("Total: " . formatarPreco($total));

        return $total;
    }
}

$produtosDisponiveis = [
    new Produto("Notebook", 3500),
    new Produto("Mouse", 150),
    new Produto("Teclado", 250),
    new Produto("Monitor", 1200),
];

$filtrarCaros = function($produto) {
    return $produto->getPreco() > 200;
};

$produtosCaros = array_filter($produtosDisponiveis, $filtrarCaros);

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

logMensagem("Total com desconto: " . formatarPreco($totalComDesconto));

$soma = array_reduce($produtosCaros, function($acumulador, $produto) {
    return $acumulador + $produto->getPreco();
}, 0);

logMensagem("Total recalculado com reduce: " . formatarPreco($soma));

function aoFinalizarPedido(callable $callback) {
    logMensagem("Finalizando pedido...");
    $callback();
}

aoFinalizarPedido(function() {
    logMensagem("Pedido finalizado com sucesso!");
});

?>