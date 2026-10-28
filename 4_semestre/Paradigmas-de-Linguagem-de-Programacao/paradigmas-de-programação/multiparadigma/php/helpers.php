<?php

function logMensagem($mensagem) {
    echo "[" . date('Y-m-d H:i:s') . "] $mensagem\n";
}

function formatarPreco($valor) {
    return "R$ " . number_format($valor, 2, ',', '.');
}

function aoFinalizarPedido(callable $callback) {
    logMensagem("Finalizando pedido...");
    $callback();
}
