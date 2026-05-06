package questao1;

public class CalculadoraLinha implements Runnable {
    private int[]   linha;
    private int     indiceLinha;
    private int     somaLinha;

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