<?php

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