/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 3)
    Elabore um programa Java que cria 3 threads. A primeira escreve na
    tela a letra “A”, a segunda escreve a letra “B” e a terceira a letra “C”.
    Faça com que seja escrito na tela sempre “ABC”

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*
*/

// Classe Monitora: Gerencia o turno de impressão usando synchronized, wait e notifyAll.
class Printer {
    // 0 = vez da A, 1 = vez da B, 2 = vez da C
    private int turn = 0;

    // Total de repetições do ciclo ABC
    private static final int ITERATIONS = 15;

    // Método para a Thread 'A'
    public synchronized void printA() throws InterruptedException {
        // Enquanto não for o turno de A (turn != 0), a thread espera.
        while (turn != 0) {
            wait(); // Libera o monitor e espera ser notificada.
        }

        // Se for o turno:
        System.out.print("A");
        turn = 1; // Passa o turno para B.
        notifyAll(); // Acorda todas as threads esperando (B e C).
    }

    // Método para a Thread 'B'
    public synchronized void printB() throws InterruptedException {
        // Enquanto não for o turno de B (turn != 1), a thread espera.
        while (turn != 1) {
            wait();
        }

        // Se for o turno:
        System.out.print("B");
        turn = 2; // Passa o turno para C.
        notifyAll(); // Acorda todas as threads.
    }

    // Método para a Thread 'C'
    public synchronized void printC() throws InterruptedException {
        // Enquanto não for o turno de C (turn != 2), a thread espera.
        while (turn != 2) {
            wait();
        }

        // Se for o turno:
        System.out.print("C");
        System.out.println(); // Adiciona uma quebra de linha após o ciclo "ABC"

        turn = 0; // Volta o turno para A.
        notifyAll(); // Acorda todas as threads.
    }

    public int getIterations() {
        return ITERATIONS;
    }
}

// Classe Runnable: Define a tarefa de cada thread
class LetterPrinter implements Runnable {
    private Printer printer;
    private char letter;

    public LetterPrinter(Printer printer, char letter) {
        this.printer = printer;
        this.letter = letter;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < printer.getIterations(); i++) {
                if (letter == 'A') {
                    printer.printA();
                } else if (letter == 'B') {
                    printer.printB();
                } else if (letter == 'C') {
                    printer.printC();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Questao3 {
    public static void main(String[] args) {
        // 1. Cria o objeto monitor que será compartilhado
        Printer sharedPrinter = new Printer();

        // 2. Cria as três threads, passando o mesmo monitor
        Thread tA = new Thread(new LetterPrinter(sharedPrinter, 'A'), "Thread-A");
        Thread tB = new Thread(new LetterPrinter(sharedPrinter, 'B'), "Thread-B");
        Thread tC = new Thread(new LetterPrinter(sharedPrinter, 'C'), "Thread-C");

        System.out.println("Iniciando a sequência controlada...");
        System.out.println("-----------------------------------");

        // 3. Inicia as threads
        // A ordem de start() não importa, pois a sincronização garante a ordem de impressão.
        tC.start();
        tA.start();
        tB.start();

        try {
            // Aguarda o término de todas as threads antes de encerrar o programa principal
            tA.join();
            tB.join();
            tC.join();
        } catch (InterruptedException e) {
            System.err.println("O programa foi interrompido.");
        }

        System.out.println("-----------------------------------");
        System.out.println("Sequência ABC finalizada após " + sharedPrinter.getIterations() + " repetições.");
    }
}