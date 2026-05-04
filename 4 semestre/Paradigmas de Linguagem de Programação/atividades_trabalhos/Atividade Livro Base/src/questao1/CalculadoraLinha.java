package questao1;

public class CalculadoraLinha implements Runnable {
    // Array que representa uma linha da matriz
    private int[]   linha;
    // Índice da linha sendo processada
    private int     indiceLinha;
    // Variável para armazenar a soma dos elementos da linha
    private int     somaLinha;

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