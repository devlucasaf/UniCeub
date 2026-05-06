package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class BackpressureDemo {
    public void demonstrateBackpressure() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println("=== Backpressure with custom subscriber ===");
        Flux<Integer> fastProducer = Flux.range(1, 1000)
                .doOnRequest(r -> System.out.println("Producer requested " + r))
                .doOnNext(i -> System.out.println("Producing " + i))
                .delayElements(Duration.ofMillis(1));

        BackpressureSubscriber<Integer> subscriber = new BackpressureSubscriber<>("SlowConsumer", 10);
        fastProducer.subscribe(subscriber);

        Thread.sleep(5000);
        System.out.println("Backpressure demo finished.");
    }

    public void onBackpressureBufferDemo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println("=== onBackpressureBuffer ===");
        Flux.interval(Duration.ofMillis(10))
                .take(200)
                .onBackpressureBuffer(50, dropped -> System.out.println("Dropped: " + dropped))
                .doOnNext(i -> {
                    if (i % 20 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // ignore
                        }
                    }
                })
                .subscribe(i -> System.out.println("Consumed " + i),
                        error -> System.err.println(error),
                        latch::countDown);
        Thread.sleep(10000);
        latch.await();
    }
}