package paradigmaconcorrenteparalelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;

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
                } else if (result.contains("X")) {
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

        ExecutorUtils.shutdownExecutor(executor);

        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        metrics.print();

        System.out.println("\nFINALIZADO ");

        new ProducerConsumerDemo().startDemo();
        new SchedulerDemo().start();
    }
}