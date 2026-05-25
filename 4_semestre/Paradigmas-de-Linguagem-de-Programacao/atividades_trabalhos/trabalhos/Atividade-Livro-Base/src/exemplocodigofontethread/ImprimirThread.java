package exemplocodigofontethread;

public class ImprimirThread extends Thread {

    private int tempoEspera;

    public ImprimirThread(String name) {
        super(name);
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

    @Override
    public void run() {
        try {
            System.out.print(getName() + " indo dormir\n");
            Thread.sleep(tempoEspera);

            for (int i = 0; i <= 5; i++) {
                System.out.print(getName() + " valor de I: " + i + "\n");
            }

            for (int j = 0; j <= 10; j++) {
                System.out.print(getName() + " valor de J: " + j + "\n");
            }
        } catch (InterruptedException exception) {
            System.out.print(exception.toString());
        }

        System.out.print(getName() + " já dormiu\n");
    }
}