package paradigmaconcorrenteparalelo;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    private final AtomicInteger completed = new AtomicInteger(0);
    private final AtomicInteger failed = new AtomicInteger(0);
    private final AtomicInteger cancelled = new AtomicInteger(0);

    public void incrementCompleted() {
        completed.incrementAndGet();
    }

    public void incrementFailed() {
        failed.incrementAndGet();
    }

    public void incrementCancelled() {
        cancelled.incrementAndGet();
    }

    public void print() {
        System.out.println("\n MÉTRICAS ");
        System.out.println("Concluídas: " + completed.get());
        System.out.println("Falhas: " + failed.get());
        System.out.println("Canceladas: " + cancelled.get());
    }
}