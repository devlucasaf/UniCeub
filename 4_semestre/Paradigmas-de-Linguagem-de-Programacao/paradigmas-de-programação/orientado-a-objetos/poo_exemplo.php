<?php

class Usuario {
    private $id;
    private $nome;
    private $email;

    public function __construct($id, $nome, $email) {
        $this->id = $id;
        $this->nome = $nome;
        $this->email = $email;
    }

    public function getId() {
        return $this->id;
    }

    public function getNome() {
        return $this->nome;
    }

    public function getEmail() {
        return $this->email;
    }
}

class Produto {
    private $id;
    private $nome;
    private $preco;

    public function __construct($id, $nome, $preco) {
        $this->id = $id;
        $this->nome = $nome;
        $this->preco = $preco;
    }

    public function getId() {
        return $this->id;
    }

    public function getNome() {
        return $this->nome;
    }

    public function getPreco() {
        return $this->preco;
    }
}

class ItemPedido {
    private $produto;
    private $quantidade;

    public function __construct(Produto $produto, $quantidade) {
        $this->produto = $produto;
        $this->quantidade = $quantidade;
    }

    public function getProduto() {
        return $this->produto;
    }

    public function getQuantidade() {
        return $this->quantidade;
    }

    public function getSubtotal() {
        return $this->produto->getPreco() * $this->quantidade;
    }
}

class Pedido {
    private $id;
    private $usuario;
    private $itens = [];
    private $status;

    public function __construct($id, Usuario $usuario) {
        $this->id = $id;
        $this->usuario = $usuario;
        $this->status = "criado";
    }

    public function adicionarItem(ItemPedido $item) {
        $this->itens[] = $item;
    }

    public function getItens() {
        return $this->itens;
    }

    public function getUsuario() {
        return $this->usuario;
    }

    public function getStatus() {
        return $this->status;
    }

    public function finalizar() {
        $this->status = "finalizado";
    }

    public function calcularTotal() {
        $total = 0;
        foreach ($this->itens as $item) {
            $total += $item->getSubtotal();
        }
        return $total;
    }
}

class Pagamento {
    protected $valor;

    public function __construct($valor) {
        $this->valor = $valor;
    }

    public function processar() {
        return false;
    }
}

class PagamentoCartao extends Pagamento {
    private $numeroCartao;

    public function __construct($valor, $numeroCartao) {
        parent::__construct($valor);
        $this->numeroCartao = $numeroCartao;
    }

    public function processar() {
        return true;
    }
}

class PagamentoBoleto extends Pagamento {
    private $codigo;

    public function __construct($valor, $codigo) {
        parent::__construct($valor);
        $this->codigo = $codigo;
    }

    public function processar() {
        return true;
    }
}

class PedidoService {
    public function gerarResumo(Pedido $pedido) {
        $dados = [];
        $dados['usuario'] = $pedido->getUsuario()->getNome();
        $dados['status'] = $pedido->getStatus();
        $dados['itens'] = [];

        foreach ($pedido->getItens() as $item) {
            $dados['itens'][] = [
                'produto' => $item->getProduto()->getNome(),
                'quantidade' => $item->getQuantidade(),
                'subtotal' => $item->getSubtotal()
            ];
        }

        $dados['total'] = $pedido->calcularTotal();

        return $dados;
    }

    public function finalizarPedido(Pedido $pedido, Pagamento $pagamento) {
        if ($pagamento->processar()) {
            $pedido->finalizar();
            return true;
        }
        return false;
    }
}

class Relatorio {
    public function imprimir(array $dados) {
        echo "Cliente: " . $dados['usuario'] . PHP_EOL;
        echo "Status: " . $dados['status'] . PHP_EOL;
        echo "Itens:" . PHP_EOL;

        foreach ($dados['itens'] as $item) {
            echo "- " . $item['produto'] . " x" . $item['quantidade'] . " = " . $item['subtotal'] . PHP_EOL;
        }

        echo "Total: " . $dados['total'] . PHP_EOL;
    }
}

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

?>