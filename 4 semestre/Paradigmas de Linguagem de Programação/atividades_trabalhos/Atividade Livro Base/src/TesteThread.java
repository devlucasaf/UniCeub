/*
Paradigmas de Linguagens de Programação
Livro Base - Capítulo 5: Programação Concorrente em Java
Página 79: Figura 23 - Exemplo de Código-Fonte em Java
*/

public class TesteThread {

    public static void main() {

        ImprimirThread thread1, thread2, thread3, thread4;
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

class ImprimirThread extends Thread {
    private int tempoEspera;
    // Construtor ImprimirThread atribui um nome ao thread
    public ImprimirThread(String name) {
        super(name);
        // dorme entre 0 e 15 segundos
        tempoEspera = (int)(Math.random() * 15000);
        System.out.println("\nNome da thread: " + getName() + " dorme: " + tempoEspera);
    }
    /*
    Exemplo prático 1

    // executa o thread
    public void run() {
        // coloca thread para dormir por um intervalo aleatório
        try {
            System.out.println(getName() + " indo dormir\n");
            Thread.sleep(tempoEspera);
        }
        catch(InterruptedException exception) {
            System.out.println(exception.toString());
        }
        // imprime o nome da thread
        System.out.println(getName() + " já dormiu\n");
    }

     */
    // Exemplo prático 2
    // executa o thread

    public void run() {
        //coloca thread para dormir por um intervalo aleatório
        try {
            System.out.print(getName() + " indor dormir\n");
            Thread.sleep(tempoEspera);
            for (int i=0; i<=5; i++) {
                System.out.print(getName() + " valor de I: " + i);
            }
            for (int j=0; j<=10; j++) {
                System.out.print(getName() + " valor de J: " + j);
            }
        }
        catch(InterruptedException exception) {
            System.out.print(exception.toString());
        }
        // Imprime o nome da thread
        System.out.print(getName() + " já dormiu\n");
    }
}
