package sincronizacaothread;

public class ConsumirInteiro extends Thread {

    private NumeroSincronizado cHold;

    public ConsumirInteiro(NumeroSincronizado h) {
        super("ConsumirInteiro");
        cHold = h;
    }

    @Override
    public void run() {

        int val, sum = 0;

        do {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }

            val = cHold.getSharedInt();
            sum += val;

        } while (val != 10);

        System.err.println(getName() + " recuperados valores totalizando: " + sum);
    }
}