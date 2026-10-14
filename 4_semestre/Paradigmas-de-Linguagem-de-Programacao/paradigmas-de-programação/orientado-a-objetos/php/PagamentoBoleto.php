<?php

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