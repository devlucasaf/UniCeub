package questao1;

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

    prompt:
        - Explique passo a passo o desenvolvimento deste 
        código Java que realiza o cálculo da soma de uma 
        matriz 4x4 de número inteiros usando threads em 
        paralelo.
*/

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random  random = new Random();
        int[][] matriz = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = random.nextInt(100); 
            }
        }

        for (int i = 0; i < 4; i++) {
            System.out.print("Linha " + i + ": ");
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

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
        } catch (InterruptedException exception) {
            System.err.println("Uma thread foi interrompida: " + exception.getMessage());
        }

        System.out.println("\n------------------------------------\n");
        System.out.println("Cálculo finalizado.");
        System.out.println("Soma total de todos os elementos da matriz: " + somaTotalMatriz);
    }
}

