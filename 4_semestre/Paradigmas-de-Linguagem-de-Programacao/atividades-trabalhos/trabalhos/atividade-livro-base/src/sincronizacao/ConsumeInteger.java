package sincronizacao;

public class ConsumeInteger extends Thread {

    private HoldIntegerUnsynchronized cHold;

    public ConsumeInteger(HoldIntegerUnsynchronized h) {
        super("ConsumeInteger");
        cHold = h;
    }

    @Override
    public void run() {

        int val;
        int sum = 0;

        do {
            try {
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }

            val = cHold.getSharedInt();
            sum += val;

        } while (val != 10);

        System.out.println(getName() +
                " recuperando valores totais: " + sum +
                "\nTerminando " + getName());
    }
}