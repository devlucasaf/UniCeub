package paradigmaconcorrenteparalelo;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerDemo {
    public void start() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("[SCHEDULER] Executando tarefa periódica: " + new Date());
        }, 0, 3, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("[SCHEDULER] Encerrando scheduler...");
            scheduler.shutdown();
        }, 15, TimeUnit.SECONDS);
    }
}