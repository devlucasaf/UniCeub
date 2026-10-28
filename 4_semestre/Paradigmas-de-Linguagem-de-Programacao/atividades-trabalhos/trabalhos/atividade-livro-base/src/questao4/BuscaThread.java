package questao4;

public class BuscaThread implements Runnable {

    private final int       alvo;
    private final int[]     vetor;
    private final int       inicio;
    private final int       fim;
    private final String    nomeThread;

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
                System.out.println(">> " + nomeThread + " ENCONTROU o valor " + alvo + " na POSIÇÃO: " + i);
                return;
            }
        }

        System.out.println(nomeThread + " finalizou a busca. Valor não encontrado neste segmento.");
    }
}