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

// Importa a classe Random para gerar números aleatórios
import java.util.Random;

/**
 * Classe que implementa um método para buscar um valor em um vetor
 * utilizando múltiplas threads simultaneamente.
 */
public class Questao4 {

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
    // CLASSE INTERNA QUE DEFINE A TAREFA DE BUSCA PARA CADA THREAD (RUNNABLE)
    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    // Classe estática interna que implementa Runnable para definir a tarefa de busca
    static class BuscaThread implements Runnable {
        // Valor que está sendo procurado no vetor (final = não pode ser alterado)
        private final int alvo;
        // Referência para o vetor onde será realizada a busca
        private final int[] vetor;
        // Índice de início do segmento que esta thread irá pesquisar
        private final int inicio;
        // Índice final (exclusivo) do segmento que esta thread irá pesquisar
        private final int fim;
        // Nome identificador da thread para facilitar o debug
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
            this.alvo = alvo;           // Atribui o valor alvo da busca
            this.vetor = vetor;         // Atribui o vetor onde buscar
            this.inicio = inicio;       // Atribui o índice de início
            this.fim = fim;             // Atribui o índice final
            this.nomeThread = nomeThread; // Atribui o nome da thread
        }

        // Método run() - será executado quando a thread for iniciada
        @Override
        public void run() {
            // Informa qual segmento do vetor esta thread irá pesquisar
            System.out.println(nomeThread + " está pesquisando de " + inicio + " até " + (fim - 1) + ".");

            // Loop que percorre o segmento do vetor atribuído a esta thread
            for (int i = inicio; i < fim; i++) {
                // Verifica se o elemento atual é igual ao valor procurado
                if (vetor[i] == alvo) {
                    // Se encontrar, mostra a mensagem e encerra a execução desta thread
                    System.out.println(">> " + nomeThread + " ENCONTROU o valor " + alvo + " na POSIÇÃO: " + i);
                    return; // Encerra a thread imediatamente após encontrar
                }
            }
            // Se o laço terminar sem encontrar o valor
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
        // Verifica se os parâmetros são válidos
        if (numThreads <= 0 || A == null || A.length == 0) {
            System.out.println("Parâmetros inválidos. A busca não pode ser realizada.");
            return; // Encerra o método se os parâmetros forem inválidos
        }

        // Obtém o tamanho total do vetor
        int N = A.length;
        // Calcula o tamanho base de cada segmento (divisão inteira)
        int tamanhoSegmento = N / numThreads;
        // Array para armazenar as threads que serão criadas
        Thread[] threads = new Thread[numThreads];

        // Loop para criar e iniciar todas as threads
        for (int i = 0; i < numThreads; i++) {
            // Calcula o índice de início do segmento para esta thread
            int inicio = i * tamanhoSegmento;
            int fim; // Variável para o índice final

            // Garante que a última thread pegue o restante (caso o tamanho não seja divisível)
            if (i == numThreads - 1) {
                fim = N; // Última thread fica com todos os elementos restantes
            } else {
                fim = inicio + tamanhoSegmento; // Threads normais ficam com segmentos iguais
            }

            // Cria um nome identificador para a thread
            String nomeThread = "Thread-" + i;

            // Cria a tarefa (Runnable) com os parâmetros calculados
            BuscaThread tarefa = new BuscaThread(x, A, inicio, fim, nomeThread);
            // Cria a thread associada à tarefa
            threads[i] = new Thread(tarefa);

            // Inicia a execução paralela da thread
            threads[i].start();
        }

        // Opcional: Aguarda todas as threads terminarem (garante que o main não encerre antes)
        try {
            // Loop para aguardar o término de cada thread
            for (Thread t : threads) {
                t.join(); // Aguarda a thread t terminar sua execução
            }
        } catch (InterruptedException e) {
            // Se houver interrupção, restaura o status de interrupção da thread atual
            Thread.currentThread().interrupt();
        }

        // Mensagem indicando que toda a busca paralela foi concluída
        System.out.println("\nBusca paralela concluída.");
    }

    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
    // EXEMPLO DE USO
    // +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // 1. Cria um vetor grande de exemplo (100 elementos)
        int tamanhoVetor = 100;
        // Declara e instancia o vetor com o tamanho especificado
        int[] vetorA = new int[tamanhoVetor];
        // Cria um objeto Random para gerar números aleatórios
        Random random = new Random();

        // Preenche o vetor com números aleatórios
        for (int i = 0; i < tamanhoVetor; i++) {
            vetorA[i] = random.nextInt(500); // Números aleatórios de 0 a 499
        }

        // 2. Define o valor a ser buscado e o número de threads
        int valorBusca = 42; // Nosso valor alvo
        int numThreads = 4;   // Número de threads para paralelizar

        // Coloca o valor de busca em um local conhecido para garantir o achado (Exemplo: posição 77)
        int posicaoConhecida = 77;
        vetorA[posicaoConhecida] = valorBusca; // Garante que o valor será encontrado

        // Mensagens iniciais explicando os parâmetros da busca
        System.out.println("Iniciando busca paralela de " + valorBusca + " no vetor de tamanho " + tamanhoVetor + " usando " + numThreads + " threads.");
        System.out.println("---");

        // 3. Chama o método de busca paralela com os parâmetros definidos
        buscaParalela(valorBusca, vetorA, numThreads);
    }
}
