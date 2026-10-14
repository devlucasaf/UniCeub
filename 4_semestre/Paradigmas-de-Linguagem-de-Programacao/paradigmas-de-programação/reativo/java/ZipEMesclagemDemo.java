package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class ZipEMesclagemDemo {
    public void executarZipDemo() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        Flux<String>   nomes = Flux.just("Ana", "Bob", "Carlos").delayElements(Duration.ofMillis(100));
        Flux<Integer>  idades = Flux.just(25, 30, 35).delayElements(Duration.ofMillis(150));

        Flux.zip(nomes, idades)
                .map(tupla -> tupla.getT1() + " tem " + tupla.getT2() + " anos")
                .subscribe(System.out::println, null, trava::countDown);
        trava.await();
    }

    public void executarMesclagemDemo() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        Flux<Integer>  fluxoA = Flux.range(1, 5).delayElements(Duration.ofMillis(50));
        Flux<Integer>  fluxoB = Flux.range(10, 5).delayElements(Duration.ofMillis(70));

        Flux.merge(fluxoA, fluxoB)
                .subscribe(i -> System.out.println("Mesclado: " + i), null, trava::countDown);
        trava.await();
    }
}