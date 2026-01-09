/*
Paradigmas de Linguagens de Programação
Livro Base - Capítulo 5: Programação Concorrente em Java
Página 81: Sincronização de Threads
*/

// Threads sem sincronização

public class SharedCell {
    public static void main(String[] args) {
        HoldIntegerUnsynchronized h = new HoldIntegerUnsynchronized();
        ProduceInteger p = new ProduceInteger(h);
        ConsumeInteger c = new ConsumeInteger(h);

        p.start();
        c.start();
    }
}

class ProduceInteger extends Thread {
    private HoldIntegerUnsynchronized pHold;
    public ProduceInteger(HoldIntegerUnsynchronized h) {
        super("ProduceInteger");
        pHold=h;
    }

    public void run() {
        for (int count=1;count<=10;count++) {
            try {
                Thread.sleep( (int) (Math.random() * 3000));
            }
            catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            pHold.setSharedInt(count);
        }
        System.out.println(getName()+ " finalizou a produção de valores" + "\n terminando " + getName());
    }
}

class ConsumeInteger extends Thread {
    private HoldIntegerUnsynchronized cHold;
    public ConsumeInteger(HoldIntegerUnsynchronized h) {
        super("ConsumeInteger");
        cHold = h;
    }
    public void run() {
        int val, sum = 0;
        do {
            try {
                Thread.sleep( (int) (Math.random() * 3000));
            }
            catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            val=cHold.getSharedInt();
            sum+=val;
        }
        while (val !=10);
        System.out.println(getName()+" recuperandovalorestotais:"+sum+ "\n Terminando " + getName());
    }
}

class HoldIntegerUnsynchronized {
    private int sharedInt = -1;
    public void setSharedInt(int val) {
        System.out.println(Thread.currentThread().getName() + " setando sharedInt para " + val);
        sharedInt = val;
    }
    public int getSharedInt() {
        System.out.println(Thread.currentThread().getName() + " recuperando valor de sharedInt " + sharedInt);
        return sharedInt;
    }
}
