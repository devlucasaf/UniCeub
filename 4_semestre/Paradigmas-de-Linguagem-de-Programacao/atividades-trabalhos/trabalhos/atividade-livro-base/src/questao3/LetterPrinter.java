package questao3;

public class LetterPrinter implements Runnable {
    private Printer printer;
    private char    letter;

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