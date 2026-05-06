package paradigmaconcorrenteparalelo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

public class ProducerConsumerDemo {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public void startDemo() {
        var executor = Executors.newFixedThreadPool(2);

        executor.execute(this::producer);
        executor.execute(this::consumer);

        ExecutorUtils.shutdownExecutor(executor);
    }

    private void producer() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i);
                System.out.println("[PRODUTOR] Produziu: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consumer() {
        try {
            for (int i = 0; i < 10; i++) {
                Integer value = queue.take();
                System.out.println("[CONSUMIDOR] Consumiu: " + value);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}