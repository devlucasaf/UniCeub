package reativo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.CountDownLatch;

public class AgendadorDemo {
    public void executarExemploParalelo() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        System.out.println("Thread principal: " + Thread.currentThread().getName());

        Flux.range(1, 10)
                .map(i -> {
                    System.out.println("Map 1 em " + Thread.currentThread().getName() + " valor " + i);
                    return i * 2;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    System.out.println("Map 2 em " + Thread.currentThread().getName() + " valor " + i);
                    return i + 1;
                })
                .subscribeOn(Schedulers.single())
                .subscribe(i -> System.out.println("Valor final " + i + " em " + Thread.currentThread().getName()),
                        erro -> System.err.println(erro),
                        trava::countDown);

        trava.await();
    }

    public void mostrarFluxoParalelo() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        Flux.range(1, 100)
                .parallel(4)
                .runOn(Schedulers.parallel())
                .map(i -> {
                    int resultado = i * i;
                    System.out.println("Computação paralela " + i + "^2=" + resultado + " em " + Thread.currentThread().getName());
                    return resultado;
                })
                .sequential()
                .collectList()
                .subscribe(lista -> {
                    System.out.println("Coletados " + lista.size() + " resultados");
                    trava.countDown();
                });
        trava.await();
    }
}