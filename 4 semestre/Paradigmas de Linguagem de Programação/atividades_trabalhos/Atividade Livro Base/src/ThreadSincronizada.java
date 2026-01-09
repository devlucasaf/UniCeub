/*
Paradigmas de Linguagens de Programação
Livro Base - Capítulo 5: Programação Concorrente em Java
Página 81: Sincronização de Threads
*/

// Threads com sincronização

public class ThreadSincronizada {
    public static void main(String[] args) {
        NumeroSincronizado h = new NumeroSincronizado();
        ProduzirInteiro p = new ProduzirInteiro(h);
        ConsumirInteiro c = new ConsumirInteiro(h);
        p.start();
        c.start();
    }
}
class ConsumirInteiro extends Thread {
    private NumeroSincronizado cHold;
    public ConsumirInteiro(NumeroSincronizado h) {
        super("ConsumirInteiro");
        cHold=h;
    }
    public void run() {
        int val, sum=0;
        do {
//dorme por um intervalo aleatório
            try {
                Thread.sleep( (int) (Math.random() * 3000));
            }
            catch (InterruptedException e) {
                System.err.println(e.toString());
            }
            val=cHold.getSharedInt();
            sum+=val;
        }
        while (val !=10);
        System.err.println(getName()+"recuperadosvalorstotalizando:"+sum);
    }
}

class ProduzirInteiro extends Thread {
    private NumeroSincronizado pHold;
    public ProduzirInteiro(NumeroSincronizado h) {
        super("ProduzirInteiro");
        pHold=h;
    }
    public void run() {
        for (int count=1; count<=10; count++) {
            try {
                Thread.sleep( (int) (Math.random() * 3000));
            }
            catch (InterruptedException e) {
                System.err.println(e.toString());
            }
            pHold.setSharedInt(count);
        }
        System.err.println(getName() + " finalizou a produção de valores ");
    }
}

class NumeroSincronizado {
    private int sharedInt = -1;
    private boolean writeable = true;

    public synchronized void setSharedInt(int val) {
        while (!writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println(Thread.currentThread().getName() + " setando valor compartilhado para " + val);
        sharedInt = val;
        writeable = false;
        notify(); //diz para um thread em espera tornar-se pronto
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
        System.err.println(Thread.currentThread().getName() + " recuperando valor compartilhado " + sharedInt);
        return sharedInt;
    }
}