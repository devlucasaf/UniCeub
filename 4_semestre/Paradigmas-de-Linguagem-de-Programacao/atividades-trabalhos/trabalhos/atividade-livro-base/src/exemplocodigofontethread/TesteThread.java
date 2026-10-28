package exemplocodigofontethread;

public class TesteThread {

    public static void main(String[] args) {

        ImprimirThread thread1;
        ImprimirThread thread2;
        ImprimirThread thread3;
        ImprimirThread thread4;

        thread1 = new ImprimirThread("Primeira thread");
        thread2 = new ImprimirThread("Segunda thread");
        thread3 = new ImprimirThread("Terceira thread");
        thread4 = new ImprimirThread("Quarta thread");

        System.out.println("\nIniciando threads");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println("\nThreads iniciadas\n");
    }
}