package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class QuenteEFrioDemo {
    public void demonstrar() throws InterruptedException {
        System.out.println("=== Demo Publisher Frio ===");
        Flux<Integer> fluxoFrio = Flux.range(1, 5).delayElements(Duration.ofMillis(100));

        fluxoFrio.subscribe(i -> System.out.println("Assinante A: " + i));
        Thread.sleep(200);
        fluxoFrio.subscribe(i -> System.out.println("Assinante B: " + i));
        Thread.sleep(1000);

        System.out.println("\n=== Demo Publisher Quente (share) ===");
        Flux<Integer> fluxoQuente = Flux.range(1, 5).delayElements(Duration.ofMillis(100)).share();

        fluxoQuente.subscribe(i -> System.out.println("Assinante X: " + i));
        Thread.sleep(200);
        fluxoQuente.subscribe(i -> System.out.println("Assinante Y: " + i));
        Thread.sleep(1000);
    }
}