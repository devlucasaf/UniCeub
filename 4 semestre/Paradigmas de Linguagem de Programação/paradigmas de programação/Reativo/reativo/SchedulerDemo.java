package reativo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.CountDownLatch;

public class SchedulerDemo {
    public void runParallelExample() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println("Main thread: " + Thread.currentThread().getName());

        Flux.range(1, 10)
                .map(i -> {
                    System.out.println("Map 1 on " + Thread.currentThread().getName() + " value " + i);
                    return i * 2;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    System.out.println("Map 2 on " + Thread.currentThread().getName() + " value " + i);
                    return i + 1;
                })
                .subscribeOn(Schedulers.single())
                .subscribe(i -> System.out.println("Final value " + i + " on " + Thread.currentThread().getName()),
                        error -> System.err.println(error),
                        latch::countDown);

        latch.await();
    }

    public void showParallelFlux() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux.range(1, 100)
                .parallel(4)
                .runOn(Schedulers.parallel())
                .map(i -> {
                    int result = i * i;
                    System.out.println("Parallel compute " + i + "^2=" + result + " on " + Thread.currentThread().getName());
                    return result;
                })
                .sequential()
                .collectList()
                .subscribe(list -> {
                    System.out.println("Collected " + list.size() + " results");
                    latch.countDown();
                });
        latch.await();
    }
}