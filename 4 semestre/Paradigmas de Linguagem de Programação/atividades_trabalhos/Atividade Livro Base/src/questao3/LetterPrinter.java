package questao3;

// Classe Runnable: Define a tarefa de cada thread
public class LetterPrinter implements Runnable {
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