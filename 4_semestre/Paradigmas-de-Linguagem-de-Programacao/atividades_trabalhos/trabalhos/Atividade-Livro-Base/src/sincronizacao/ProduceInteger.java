package sincronizacao;

public class ProduceInteger extends Thread {

    private HoldIntegerUnsynchronized pHold;

    public ProduceInteger(HoldIntegerUnsynchronized h) {
        super("ProduceInteger");
        pHold = h;
    }

    @Override
    public void run() {

        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }

            pHold.setSharedInt(count);
        }

        System.out.println(getName() +
                " finalizou a produção de valores\nTerminando " + getName());
    }
}