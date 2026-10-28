package questao3;

public class Printer {
    private                 int                 turn = 0;
    private static final    int                 ITERATIONS = 15;

    public synchronized void printA() throws InterruptedException {
        while (turn != 0) {
            wait(); 
        }

        System.out.print("A");
        turn = 1; 
        notifyAll(); 
    }

    public synchronized void printB() throws InterruptedException {
        
        while (turn != 1) {
            wait(); 
        }

        System.out.print("B");
        turn = 2; 
        notifyAll(); 
    }

    public synchronized void printC() throws InterruptedException {
        while (turn != 2) {
            wait(); 
        }

        System.out.print("C");
        System.out.println(); 

        turn = 0; 
        notifyAll(); 
    }

    public int getIterations() {
        return ITERATIONS; 
    }
}