package sincronizacaothread;

public class ProduzirInteiro extends Thread {

    private NumeroSincronizado pHold;

    public ProduzirInteiro(NumeroSincronizado h) {
        super("ProduzirInteiro");
        pHold = h;
    }

    @Override
    public void run() {

        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
            }

            pHold.setSharedInt(count);
        }

        System.err.println(getName() + " finalizou a produção de valores");
    }
}