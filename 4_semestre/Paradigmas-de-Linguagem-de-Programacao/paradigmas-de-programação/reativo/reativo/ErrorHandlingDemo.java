package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ErrorHandlingDemo {
    public void runExamples() {
        System.out.println("=== onErrorReturn ===");
        Flux<Integer> fluxWithError = Flux.just(1, 2, 3)
                .map(i -> {
                    if (i == 2) {
                        throw new RuntimeException("Simulated error");
                    }
                    return i;
                })
                .onErrorReturn(-1);
        fluxWithError.subscribe(System.out::println);

        System.out.println("\n=== onErrorResume ===");
        Flux.just(1, 2, 3)
                .map(i -> {
                    if (i == 3) {
                        throw new RuntimeException("Failure");
                    }
                    return i;
                })
                .onErrorResume(e -> Flux.just(10, 20, 30))
                .subscribe(System.out::println);

        System.out.println("\n=== retry ===");
        AtomicInteger counter = new AtomicInteger(0);
        Flux.just(1, 2, 3)
                .map(i -> {
                    if (counter.incrementAndGet() < 3 && i == 2) {
                        throw new RuntimeException("Retry me");
                    }
                    return i;
                })
                .retry(2)
                .subscribe(System.out::println, e -> System.out.println("Final error: " + e));

        System.out.println("\n=== timeout ===");
        Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(200))
                .timeout(Duration.ofMillis(100))
                .onErrorReturn(999)
                .subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }
}