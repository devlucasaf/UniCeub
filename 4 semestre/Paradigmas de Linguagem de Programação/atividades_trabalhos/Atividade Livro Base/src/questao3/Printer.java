package questao3;

// Classe Monitora: Gerencia o turno de impressão usando synchronized, wait e notifyAll.
public class Printer {
    // Variável que controla de quem é a vez: 0 = vez da A, 1 = vez da B, 2 = vez da C
    private int turn = 0;

    // Constante que define o total de repetições do ciclo ABC (15 vezes)
    private static final int ITERATIONS = 15;

    // Método sincronizado para a Thread 'A' - só uma thread pode executar por vez
    public synchronized void printA() throws InterruptedException {
        // Enquanto não for o turno de A (turn != 0), a thread espera.
        while (turn != 0) {
            wait(); // Libera o monitor e espera ser notificada.
        }

        // Se for o turno de A, imprime a letra
        System.out.print("A");
        turn = 1; // Passa o turno para B.
        notifyAll(); // Acorda todas as threads esperando (B e C).
    }

    // Método sincronizado para a Thread 'B'
    public synchronized void printB() throws InterruptedException {
        // Enquanto não for o turno de B (turn != 1), a thread espera.
        while (turn != 1) {
            wait(); // Libera o monitor e espera ser notificada.
        }

        // Se for o turno de B, imprime a letra
        System.out.print("B");
        turn = 2; // Passa o turno para C.
        notifyAll(); // Acorda todas as threads esperando (A e C).
    }

    // Método sincronizado para a Thread 'C'
    public synchronized void printC() throws InterruptedException {
        // Enquanto não for o turno de C (turn != 2), a thread espera.
        while (turn != 2) {
            wait(); // Libera o monitor e espera ser notificada.
        }

        // Se for o turno de C, imprime a letra
        System.out.print("C");
        System.out.println(); // Adiciona uma quebra de linha após completar o ciclo "ABC"

        turn = 0; // Volta o turno para A, reiniciando o ciclo.
        notifyAll(); // Acorda todas as threads esperando (A e B).
    }

    // Método getter para obter o número total de iterações
    public int getIterations() {
        return ITERATIONS; // Retorna o valor da constante ITERATIONS (15)
    }
}