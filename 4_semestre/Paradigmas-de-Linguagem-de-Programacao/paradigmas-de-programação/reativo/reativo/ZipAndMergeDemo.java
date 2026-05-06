package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class ZipAndMergeDemo {
    public void runZipDemo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux<String> names = Flux.just("Ana", "Bob", "Charlie").delayElements(Duration.ofMillis(100));
        Flux<Integer> ages = Flux.just(25, 30, 35).delayElements(Duration.ofMillis(150));

        Flux.zip(names, ages)
                .map(tuple -> tuple.getT1() + " has " + tuple.getT2() + " years")
                .subscribe(System.out::println, null, latch::countDown);
        latch.await();
    }

    public void runMergeDemo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux<Integer> fluxA = Flux.range(1, 5).delayElements(Duration.ofMillis(50));
        Flux<Integer> fluxB = Flux.range(10, 5).delayElements(Duration.ofMillis(70));

        Flux.merge(fluxA, fluxB)
                .subscribe(i -> System.out.println("Merged: " + i), null, latch::countDown);
        latch.await();
    }
}