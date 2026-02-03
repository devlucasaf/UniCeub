import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Task implements Callable<String> {
    private final int id;
    private final int duration;

    public Task(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Iniciando tarefa " + id + " (duração " + duration + "s)");
        Thread.sleep(duration * 1000); // simula tempo de execução
        return "Tarefa " + id + " concluída em " + duration + " segundos";
    }
}

public class ParadigmaConcorrente {
    public static void main(String[] args) {
        Random random = new Random();

        // ExecutorService gerencia um pool de threads
        int numWorkers = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);

        List<Future<String>> futures = new ArrayList<>();

        // Criando e enviando tarefas
        int numTasks = 8;
        for (int i = 1; i <= numTasks; i++) {
            int duration = random.nextInt(3) + 1; // entre 1 e 3 segundos
            Task task = new Task(i, duration);
            Future<String> future = executor.submit(task);
            futures.add(future);
            System.out.println("Tarefa " + i + " enviada para execução");
        }

        // Coletando resultados
        for (Future<String> future : futures) {
            try {
                String result = future.get(); // bloqueia até a tarefa terminar
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Encerrando o executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Todas as tarefas foram processadas!");
    }
}
