<?php

class PedidoService {
    public function gerarResumo(Pedido $pedido) {
        $dados = [];
        $dados["usuario"] = $pedido->getUsuario()->getNome();
        $dados["status"] = $pedido->getStatus();
        $dados["itens"] = [];

        foreach ($pedido->getItens() as $item) {
            $dados["itens"][] = [
                "produto" => $item->getProduto()->getNome(),
                "quantidade" => $item->getQuantidade(),
                "subtotal" => $item->getSubtotal()
            ];
        }

        $dados["total"] = $pedido->calcularTotal();

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