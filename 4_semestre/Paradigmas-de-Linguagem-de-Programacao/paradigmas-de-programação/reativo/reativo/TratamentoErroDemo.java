package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class TratamentoErroDemo {
    public void executarExemplos() {
        System.out.println("=== onErrorReturn ===");
        Flux<Integer> fluxoComErro = Flux.just(1, 2, 3)
                .map(i -> {
                    if (i == 2) {
                        throw new RuntimeException("Erro simulado");
                    }
                    return i;
                })
                .onErrorReturn(-1);
        fluxoComErro.subscribe(System.out::println);

        System.out.println("\n=== onErrorResume ===");
        Flux.just(1, 2, 3)
                .map(i -> {
                    if (i == 3) {
                        throw new RuntimeException("Falha");
                    }
                    return i;
                })
                .onErrorResume(e -> Flux.just(10, 20, 30))
                .subscribe(System.out::println);

        System.out.println("\n=== retry ===");
        AtomicInteger contador = new AtomicInteger(0);
        Flux.just(1, 2, 3)
                .map(i -> {
                    if (contador.incrementAndGet() < 3 && i == 2) {
                        throw new RuntimeException("Tente novamente");
                    }
                    return i;
                })
                .retry(2)
                .subscribe(System.out::println, e -> System.out.println("Erro final: " + e));

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