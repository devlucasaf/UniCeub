package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class HotAndColdDemo {
    public void demonstrate() throws InterruptedException {
        System.out.println("=== Cold Publisher Demo ===");
        Flux<Integer> coldFlux = Flux.range(1, 5).delayElements(Duration.ofMillis(100));

        coldFlux.subscribe(i -> System.out.println("Subscriber A: " + i));
        Thread.sleep(200);
        coldFlux.subscribe(i -> System.out.println("Subscriber B: " + i));
        Thread.sleep(1000);

        System.out.println("\n=== Hot Publisher Demo (share) ===");
        Flux<Integer> hotFlux = Flux.range(1, 5).delayElements(Duration.ofMillis(100)).share();

        hotFlux.subscribe(i -> System.out.println("Subscriber X: " + i));
        Thread.sleep(200);
        hotFlux.subscribe(i -> System.out.println("Subscriber Y: " + i));
        Thread.sleep(1000);
    }
}