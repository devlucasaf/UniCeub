<?php

class Pagamento {
    protected $valor;

    public function __construct($valor) {
        $this->valor = $valor;
    }

    public function processar() {
        return false;
    }
}