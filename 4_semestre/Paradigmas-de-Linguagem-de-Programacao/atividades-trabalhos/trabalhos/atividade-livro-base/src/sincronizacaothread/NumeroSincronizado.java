package sincronizacaothread;

public class NumeroSincronizado {

    private int     sharedInt = -1;
    private boolean writeable = true;

    public synchronized void setSharedInt(int val) {
        while (!writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.err.println(Thread.currentThread().getName() +
                " setando valor compartilhado para " + val);

        sharedInt = val;
        writeable = false;

        notify();
    }

    public synchronized int getSharedInt() {
        while (writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        writeable = true;
        notify();

        System.err.println(Thread.currentThread().getName() +
                " recuperando valor compartilhado " + sharedInt);

        return sharedInt;
    }
}