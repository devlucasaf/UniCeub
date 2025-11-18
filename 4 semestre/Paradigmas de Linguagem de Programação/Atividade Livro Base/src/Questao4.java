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

/**
    Classe que implementa um método para buscar um valor em um vetor
    utilizando múltiplas threads simultaneamente.
*/

public class Questao4 {

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    // CLASSE INTERNA QUE DEFINE A TAREFA DE BUSCA PARA CADA THREAD (RUNNABLE)

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    static class BuscaThread implements Runnable {
        private final int alvo;
        private final int[] vetor;
        private final int inicio;
        private final int fim; // O índice 'fim' é exclusivo (não é percorrido)
        private final String nomeThread;

        /**
            * Construtor da Thread de Busca.
            * @param alvo O número a ser procurado.
            * @param vetor O array principal.
            * @param inicio O índice de início da busca no vetor.
            * @param fim O índice final (exclusivo) da busca no vetor.
            * @param nomeThread Nome identificador da thread.
        */

        public BuscaThread(int alvo, int[] vetor, int inicio, int fim, String nomeThread) {
            this.alvo = alvo;
            this.vetor = vetor;
            this.inicio = inicio;
            this.fim = fim;
            this.nomeThread = nomeThread;
        }

        @Override
        public void run() {
            System.out.println(nomeThread + " está pesquisando de " + inicio + " até " + (fim - 1) + ".");

            for (int i = inicio; i < fim; i++) {
                if (vetor[i] == alvo) {
                    // Se encontrar, mostra a mensagem e encerra a execução desta thread.
                    System.out.println(">> " + nomeThread + " ENCONTROU o valor " + alvo + " na POSIÇÃO: " + i);
                    return;
                }
            }
            // Se o laço terminar sem encontrar
            System.out.println(nomeThread + " finalizou a busca. Valor não encontrado neste segmento.");
        }
    }

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    // MÉTODO PRINCIPAL DE PARALELIZAÇÃO

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    /**
     * Paraleliza a busca de um valor X dentro do vetor A usando um número
     * especificado de threads.
     * @param x O valor inteiro a ser procurado.
     * @param A O vetor de inteiros onde a busca será realizada.
     * @param numThreads O número de threads a serem criadas.
     */

    public static void buscaParalela(int x, int[] A, int numThreads) {
        if (numThreads <= 0 || A == null || A.length == 0) {
            System.out.println("Parâmetros inválidos. A busca não pode ser realizada.");
            return;
        }

        int N = A.length;
        // Calcula o tamanho base de cada segmento
        int tamanhoSegmento = N / numThreads;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int inicio = i * tamanhoSegmento;
            int fim;

            // Garante que a última thread pegue o restante (caso o tamanho não seja divisível)
            if (i == numThreads - 1) {
                fim = N;
            } else {
                fim = inicio + tamanhoSegmento;
            }

            String nomeThread = "Thread-" + i;

            // Cria a tarefa e a thread
            BuscaThread tarefa = new BuscaThread(x, A, inicio, fim, nomeThread);
            threads[i] = new Thread(tarefa);

            // Inicia a execução paralela
            threads[i].start();
        }

        // Opcional: Aguarda todas as threads terminarem (garante que o main não encerre antes)
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nBusca paralela concluída.");
    }

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    // EXEMPLO DE USO

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    public static void main(String[] args) {
        // 1. Cria um vetor grande de exemplo (100 elementos)
        int tamanhoVetor = 100;
        int[] vetorA = new int[tamanhoVetor];
        Random random = new Random();

        for (int i = 0; i < tamanhoVetor; i++) {
            vetorA[i] = random.nextInt(500); // Números aleatórios de 0 a 499
        }

        // 2. Define o valor a ser buscado e o número de threads
        int valorBusca = 42; // Nosso valor alvo
        int numThreads = 4;   // Número de threads para paralelizar

        // Coloca o valor de busca em um local conhecido para garantir o achado (Exemplo: posição 77)
        int posicaoConhecida = 77;
        vetorA[posicaoConhecida] = valorBusca;

        System.out.println("Iniciando busca paralela de " + valorBusca + " no vetor de tamanho " + tamanhoVetor + " usando " + numThreads + " threads.");
        System.out.println("---");

        // 3. Chama o método de busca paralela
        buscaParalela(valorBusca, vetorA, numThreads);
    }
}
