/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 1)
    Escreva um programa em Java que realize o cálculo das somas dos
    valores de uma matriz 4x4 de números inteiros e imprima o resultado
    destas somas na tela. Faça com que o cálculo do somatório de cada
    linha seja realizado emparalelo, porthreads;

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*
*/

class CalculadoraLinha implements Runnable {
    private int[] linha;
    private int indiceLinha;
    private int somaLinha;

    public CalculadoraLinha(int[] linha, int indiceLinha) {
        this.linha = linha;
        this.indiceLinha = indiceLinha;
        this.somaLinha = 0;
    }
    @Override
    public void run() {
        System.out.println("Thread iniciada para a linha " + indiceLinha + "...");

        for (int numero : linha) {
            somaLinha += numero;
        }
        System.out.println(">> Soma da Linha " + indiceLinha + ": " + somaLinha);
    }

    public int getSomaLinha() {
        return somaLinha;
    }
}

public class Questao1 {
    public static void main() {
        int[][] matriz = {
                {10, 5, 2, 3},
                {1, 1, 1, 1},
                {10, 10, 10, 10},
                {50, 20, 0, 5}
        };

        Thread[] threads = new Thread[4];
        CalculadoraLinha[] tarefas = new CalculadoraLinha[4];

        System.out.println(">>> Iniciando Cálculos Paralelos <<<");

        for (int i = 0; i < 4; i++) {
            tarefas[i] = new CalculadoraLinha(matriz[i], i);

            threads[i] = new Thread(tarefas[i]);

            threads[i].start();
        }
        int somaTotalMatriz = 0;

        try {
            for (int i = 0; i < 4; i++) {
                threads[i].join();

                somaTotalMatriz += tarefas[i].getSomaLinha();
            }
        }
        catch (InterruptedException exception) {
            System.err.println("Uma thread foi interrompida: " + exception.getMessage());
        }

        System.out.println("\n------------------------------------\n");
        System.out.println("Cálculo finalizado.");
        System.out.println("Soma TOTAL de todos os elementos da matriz: " + somaTotalMatriz);
    }
}
