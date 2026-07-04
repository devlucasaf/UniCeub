<?php

class Relatorio {
    public function imprimir(array $dados) {
        echo "Cliente: " . $dados["usuario"] . PHP_EOL;
        echo "Status: " . $dados["status"] . PHP_EOL;
        echo "Itens:" . PHP_EOL;

        foreach ($dados["itens"] as $item) {
            echo "- " . $item["produto"] . " x" . $item["quantidade"] . " = " . $item["subtotal"] . PHP_EOL;
        }

        echo "Total: " . $dados["total"] . PHP_EOL;
    }
}