package reativo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

public class FluxoComplexoPipeline {
    private final SimuladorDados simulador = new SimuladorDados();
    private final ProcessadorDados processador = new ProcessadorDados();

    public Flux<String> construirPipeline(int eventos, long intervaloMs) {
        return simulador.fluxoDadosSensor(intervaloMs, eventos)
                .doOnNext(dado -> System.out.println("[BRUTO] " + dado))
                .filter(dado -> dado.getValor() > 0)
                .map(dado -> {
                    if (dado.getIdSensor().contains("TEMP")) {
                        return new DadoSensor(dado.getIdSensor(), dado.getValor() * 1.8 + 32, dado.getTimestamp());
                    }
                    return dado;
                })
                .flatMap(dado -> {
                    if (dado.getIdSensor().equals("PRESS-01")) {
                        return Mono.just(dado).delayElement(Duration.ofMillis(10));
                    }
                    return Mono.just(dado);
                })
                .transform(fluxo -> processador.calcularMediaMovel(fluxo, 5))
                .transform(processador::detectarAnomalias)
                .flatMap(alerta -> {
                    if (alerta.gravidade == Gravidade.CRITICO) {
                        return Mono.just("CRÍTICO: " + alerta.mensagem).delayElement(Duration.ofMillis(5));
                    } else {
                        return Mono.just("AVISO: " + alerta.mensagem);
                    }
                })
                .onErrorResume(e -> Mono.just("Recuperado de erro: " + e.getMessage()));
    }
}