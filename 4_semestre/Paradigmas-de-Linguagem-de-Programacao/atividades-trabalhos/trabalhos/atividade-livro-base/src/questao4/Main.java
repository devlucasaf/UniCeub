package questao4;

/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 4)
        Escreva uma classe em Java que permita paralelizar uma pesquisa em
        um vetor de número inteiros. Isso deve ser feito com o seguinte
        método: public buscaParalela(int x, int[] A, int numThreads). Este
        método cria tantas threads quanto especificadas em numThreads,
        divide o array A em muitas partes e dá a cada thread parte do array
        para procurarsequencialmente pelo valor X. Se uma thread encontrar
        o valor X, deve mostrar uma mensagem indicando a posição onde o
        valorfoi encontrado

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*
*/

import java.util.Random;

public class Main {

    public static void buscaParalela(int x, int[] A, int numThreads) {

        if (numThreads <= 0 || A == null || A.length == 0) {
            System.out.println("Parâmetros inválidos. A busca não pode ser realizada.");
            return;
        }

        int N = A.length;
        int tamanhoSegmento = N / numThreads;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {

            int inicio = i * tamanhoSegmento;
            int fim;

            if (i == numThreads - 1) {
                fim = N;
            } else {
                fim = inicio + tamanhoSegmento;
            }

            String nomeThread = "Thread-" + i;

            BuscaThread tarefa = new BuscaThread(x, A, inicio, fim, nomeThread);
            threads[i] = new Thread(tarefa);

            threads[i].start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nBusca paralela concluída.");
    }

    public static void main(String[] args) {

        int tamanhoVetor = 100;
        int[] vetorA = new int[tamanhoVetor];

        Random random = new Random();

        for (int i = 0; i < tamanhoVetor; i++) {
            vetorA[i] = random.nextInt(500);
        }

        int valorBusca = 42;
        int numThreads = 4;

        int posicaoConhecida = 77;
        vetorA[posicaoConhecida] = valorBusca;

        System.out.println("Iniciando busca paralela de " + valorBusca +
                " no vetor de tamanho " + tamanhoVetor +
                " usando " + numThreads + " threads.");
        System.out.println("---");

        buscaParalela(valorBusca, vetorA, numThreads);
    }
}