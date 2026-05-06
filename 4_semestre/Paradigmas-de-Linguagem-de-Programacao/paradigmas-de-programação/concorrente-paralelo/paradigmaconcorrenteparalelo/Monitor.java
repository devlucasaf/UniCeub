package paradigmaconcorrenteparalelo;

import java.util.concurrent.ThreadPoolExecutor;

public class Monitor implements Runnable {
    private final ThreadPoolExecutor executor;

    public Monitor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            while (!executor.isTerminated()) {
                System.out.println("[MONITOR] Pool size: " + executor.getPoolSize() +
                        " | Active: " + executor.getActiveCount() +
                        " | Completed: " + executor.getCompletedTaskCount() +
                        " | Queue: " + executor.getQueue().size());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}