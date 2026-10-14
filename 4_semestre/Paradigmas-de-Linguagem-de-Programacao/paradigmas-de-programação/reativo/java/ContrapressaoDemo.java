package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class ContrapressaoDemo {
    public void demonstrarContrapressao() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        System.out.println("=== Contrapressão com assinante personalizado ===");
        Flux<Integer> produtorRapido = Flux.range(1, 1000)
                .doOnRequest(r -> System.out.println("Produtor solicitou " + r))
                .doOnNext(i -> System.out.println("Produzindo " + i))
                .delayElements(Duration.ofMillis(1));

        AssinanteContrapressao<Integer> assinante = new AssinanteContrapressao<>("ConsumidorLento", 10);
        produtorRapido.subscribe(assinante);

        Thread.sleep(5000);
        System.out.println("Demo de contrapressão finalizado.");
    }

    public void demonstrarBufferContrapressao() throws InterruptedException {
        CountDownLatch trava = new CountDownLatch(1);
        System.out.println("=== onBackpressureBuffer ===");
        Flux.interval(Duration.ofMillis(10))
                .take(200)
                .onBackpressureBuffer(50, descartado -> System.out.println("Descartado: " + descartado))
                .doOnNext(i -> {
                    if (i % 20 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {}
                    }
                })
                .subscribe(i -> System.out.println("Consumido " + i),
                        erro -> System.err.println(erro),
                        trava::countDown);
        Thread.sleep(10000);
        trava.await();
    }
}