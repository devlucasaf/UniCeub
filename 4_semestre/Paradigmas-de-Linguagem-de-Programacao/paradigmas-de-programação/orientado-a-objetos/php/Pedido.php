<?php

class Pedido {
    private $id;
    private $usuario;
    private $itens = [];
    private $status;

    public function __construct($id, Usuario $usuario) {
        $this->id       = $id;
        $this->usuario  = $usuario;
        $this->status   = "criado";
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