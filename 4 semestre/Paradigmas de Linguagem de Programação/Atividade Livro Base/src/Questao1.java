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

// Classe que implementa Runnable para executar o cálculo de soma em uma thread
class CalculadoraLinha implements Runnable {
    // Array que representa uma linha da matriz
    private int[] linha;
    // Índice da linha sendo processada
    private int indiceLinha;
    // Variável para armazenar a soma dos elementos da linha
    private int somaLinha;

    // Construtor da classe - recebe uma linha da matriz e seu índice
    public CalculadoraLinha(int[] linha, int indiceLinha) {
        // Atribui a linha recebida ao atributo da classe
        this.linha = linha;
        // Atribui o índice da linha recebido ao atributo da classe
        this.indiceLinha = indiceLinha;
        // Inicializa a soma da linha com zero
        this.somaLinha = 0;
    }

    // Método run() - será executado quando a thread for iniciada
    @Override
    public void run() {
        // Imprime mensagem indicando que a thread foi iniciada
        System.out.println("Thread iniciada para a linha " + indiceLinha + "...");

        // Loop para percorrer todos os elementos da linha
        for (int numero : linha) {
            // Soma cada número ao total da linha
            somaLinha += numero;
        }
        // Imprime o resultado da soma para esta linha específica
        System.out.println(">> Soma da Linha " + indiceLinha + ": " + somaLinha);
    }

    // Método getter para obter o valor da soma da linha
    public int getSomaLinha() {
        // Retorna o valor calculado da soma da linha
        return somaLinha;
    }
}

// Classe principal que contém o método main
public class Questao1 {
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // Declara e inicializa uma matriz 4x4 com valores inteiros
        Random random = new Random();
        int[][] matriz = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = random.nextInt(100); // gera número entre 0 a 99
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

        // Array para armazenar as 4 threads que serão criadas
        Thread[] threads = new Thread[4];
        // Array para armazenar as 4 tarefas (CalculadoraLinha)
        CalculadoraLinha[] tarefas = new CalculadoraLinha[4];

        // Imprime mensagem indicando o início dos cálculos
        System.out.println(">>> Iniciando Cálculos Paralelos <<<");

        // Loop para criar e iniciar as 4 threads
        for (int i = 0; i < 4; i++) {
            // Cria uma nova tarefa CalculadoraLinha para a linha i da matriz
            tarefas[i] = new CalculadoraLinha(matriz[i], i);

            // Cria uma nova thread associada à tarefa
            threads[i] = new Thread(tarefas[i]);

            // Inicia a execução da thread (chama o método run())
            threads[i].start();
        }

        // Variável para armazenar a soma total de todos os elementos da matriz
        int somaTotalMatriz = 0;

        // Bloco try-catch para tratar possíveis interrupções das threads
        try {
            // Loop para aguardar a finalização de todas as threads
            for (int i = 0; i < 4; i++) {
                // Aguarda a thread i terminar sua execução (método bloqueante)
                threads[i].join();

                // Adiciona a soma da linha i ao total geral da matriz
                somaTotalMatriz += tarefas[i].getSomaLinha();
            }
        }
        // Captura exceção caso alguma thread seja interrompida
        catch (InterruptedException exception) {
            // Imprime mensagem de erro informando qual thread foi interrompida
            System.err.println("Uma thread foi interrompida: " + exception.getMessage());
        }

        // Imprime separador visual para organizar a saída
        System.out.println("\n------------------------------------\n");
        // Imprime mensagem indicando que o cálculo foi finalizado
        System.out.println("Cálculo finalizado.");
        // Imprime o resultado final da soma de todos os elementos da matriz
        System.out.println("Soma total de todos os elementos da matriz: " + somaTotalMatriz);
    }
}

