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

// Classe Runnable: Define a tarefa de cada thread
class LetterPrinter implements Runnable {
    // Referência para o objeto Printer compartilhado entre as threads
    private Printer printer;
    // Letra que esta thread será responsável por imprimir (A, B ou C)
    private char letter;

    // Construtor que recebe o Printer compartilhado e a letra da thread
    public LetterPrinter(Printer printer, char letter) {
        this.printer = printer; // Atribui o objeto Printer
        this.letter = letter;   // Atribui a letra correspondente
    }

    // Método run() - será executado quando a thread for iniciada
    @Override
    public void run() {
        try {
            // Loop que executa o número total de iterações definido no Printer
            for (int i = 0; i < printer.getIterations(); i++) {
                // Verifica qual letra esta thread representa e chama o método correspondente
                if (letter == 'A') {
                    printer.printA(); // Chama o método para imprimir A
                } else if (letter == 'B') {
                    printer.printB(); // Chama o método para imprimir B
                } else if (letter == 'C') {
                    printer.printC(); // Chama o método para imprimir C
                }
            }
        } catch (InterruptedException e) {
            // Se a thread for interrompida, restaura o status de interrupção
            Thread.currentThread().interrupt();
        }
    }
}

// Classe principal que contém o método main
public class Questao3 {
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // 1. Cria o objeto monitor que será compartilhado entre todas as threads
        Printer sharedPrinter = new Printer();

        // 2. Cria as três threads, passando o mesmo monitor compartilhado
        // Cada thread recebe uma tarefa LetterPrinter com sua letra correspondente
        Thread tA = new Thread(new LetterPrinter(sharedPrinter, 'A'), "Thread-A");
        Thread tB = new Thread(new LetterPrinter(sharedPrinter, 'B'), "Thread-B");
        Thread tC = new Thread(new LetterPrinter(sharedPrinter, 'C'), "Thread-C");

        // Mensagens iniciais do programa
        System.out.println("Iniciando a sequência controlada...");
        System.out.println("-----------------------------------");

        // 3. Inicia as threads
        // A ordem de start() não importa, pois a sincronização garante a ordem de impressão ABC.
        tC.start(); // Inicia a thread C
        tA.start(); // Inicia a thread A
        tB.start(); // Inicia a thread B

        // Bloco try-catch para aguardar o término de todas as threads
        try {
            // Aguarda a thread A terminar sua execução
            tA.join();
            // Aguarda a thread B terminar sua execução
            tB.join();
            // Aguarda a thread C terminar sua execução
            tC.join();
        } catch (InterruptedException e) {
            // Captura exceção caso o programa principal seja interrompido durante o join
            System.err.println("O programa foi interrompido.");
        }

        // Mensagens finais do programa
        System.out.println("-----------------------------------");
        System.out.println("Sequência ABC finalizada após " + sharedPrinter.getIterations() + " repetições.");
    }
}
