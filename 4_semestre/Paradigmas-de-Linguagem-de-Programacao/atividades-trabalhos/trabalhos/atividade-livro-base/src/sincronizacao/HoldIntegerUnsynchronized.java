package sincronizacao;

public class HoldIntegerUnsynchronized {

    private int sharedInt = -1;

    public int getSharedInt() {
        System.out.println(Thread.currentThread().getName() +
                " recuperando valor de sharedInt " + sharedInt);
        return sharedInt;
    }

    public void setSharedInt(int val) {
        System.out.println(Thread.currentThread().getName() +
                " setando sharedInt para " + val);
        sharedInt = val;
    }
}