import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.atomic.AtomicInteger;

class Task implements Callable<String> {
    private final int       id;
    private final int       duration;
    private final Semaphore semaphore;

    public Task(int id, int duration, Semaphore semaphore) {
        this.id = id;
        this.duration = duration;
        this.semaphore = semaphore;
    }

    @Override
    public String call() {
        try {
            semaphore.acquire(); 
            log("Iniciando tarefa " + id + " (" + duration + "s)");

            for (int i = 1; i <= duration; i++) {
                Thread.sleep(1000);
                log("Tarefa " + id + " progresso: " + i + "s");
            }

            if (new Random().nextInt(20) == 0) {
                throw new RuntimeException("Erro aleatório na tarefa " + id);
            }

            return "Tarefa " + id + " concluída em " + duration + " segundos";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Tarefa " + id + " interrompida";
        } catch (Exception e) {
            return "Tarefa " + id + " falhou: " + e.getMessage();
        } finally {
            semaphore.release();
        }
    }

    private void log(String msg) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + msg);
    }
}

class Metrics {
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

class Monitor implements Runnable {
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

public class ParadigmaConcorrente {

    public static void main(String[] args) {
        int numWorkers = 4;
        int numTasks = 20;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numWorkers);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        Semaphore semaphore = new Semaphore(3);
        Metrics metrics = new Metrics();

        List<Future<String>> futures = new ArrayList<>();

        Thread monitorThread = new Thread(new Monitor(executor));
        monitorThread.start();

        Random random = new Random();

        System.out.println(" ENVIANDO TAREFAS ");

        for (int i = 1; i <= numTasks; i++) {
            int duration = random.nextInt(5) + 1;
            Task task = new Task(i, duration, semaphore);

            Future<String> future = completionService.submit(task);
            futures.add(future);

            System.out.println("Tarefa " + i + " enviada");
        }

        System.out.println("\n PROCESSANDO RESULTADOS ");

        for (int i = 0; i < numTasks; i++) {
            try {
                Future<String> completedFuture = completionService.take();
                String result = completedFuture.get();

                System.out.println(result);

                if (result.contains("✔")) {
                    metrics.incrementCompleted();
                } else if (result.contains("❌")) {
                    metrics.incrementFailed();
                } else {
                    metrics.incrementCancelled();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                metrics.incrementFailed();
                System.out.println("Erro ao executar tarefa: " + e.getMessage());
            }
        }

        System.out.println("\nCANCELAMENTO DE TAREFAS (EXEMPLO) ");
        for (Future<String> future : futures) {
            if (!future.isDone()) {
                future.cancel(true);
                metrics.incrementCancelled();
            }
        }

        shutdownExecutor(executor);

        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        metrics.print();

        System.out.println("\nFINALIZADO ");
    }

    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Forçando encerramento...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    static class ProducerConsumerDemo {
        private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        public void startDemo() {
            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(this::producer);
            executor.execute(this::consumer);

            shutdownExecutor(executor);
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

    static class SchedulerDemo {
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
}