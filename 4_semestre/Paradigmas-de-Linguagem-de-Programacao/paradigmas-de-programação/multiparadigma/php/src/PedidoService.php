<?php

class PedidoService {

    public function aplicarDesconto($total, $percentual) {
        return $total - ($total * ($percentual / 100));
    }

    public function gerarResumo(Pedido $pedido) {
        $produtos = $pedido->listarProdutos();

        logMensagem("Resumo do Pedido:");

        foreach ($produtos as $produto) {
            logMensagem(
                "- " .
                $produto->getNome() .
                " | " .
                formatarPreco($produto->getPreco())
            );
        }

        $total = $pedido->calcularTotal();

        logMensagem("Total: " . formatarPreco($total));

        return $total;
    }
}
