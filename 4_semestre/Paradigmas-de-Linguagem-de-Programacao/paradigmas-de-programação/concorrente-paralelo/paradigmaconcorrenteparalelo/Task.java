package paradigmaconcorrenteparalelo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Task implements java.util.concurrent.Callable<String> {
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