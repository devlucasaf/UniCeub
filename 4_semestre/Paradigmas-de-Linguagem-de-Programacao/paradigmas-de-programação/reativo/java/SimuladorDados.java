package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SimuladorDados {
    private final Random aleatorio = new Random();
    private final List<String> idsSensores = List.of("TEMP-01", "TEMP-02", "PRESS-01", "HUM-01");

    public Flux<DadoSensor> fluxoDadosSensor(long intervaloMillis, int maxEventos) {
        return Flux.interval(Duration.ofMillis(intervaloMillis))
                .take(maxEventos)
                .map(tick -> {
                    String idSensor = idsSensores.get(aleatorio.nextInt(idsSensores.size()));
                    double valor;

                    if (idSensor.startsWith("TEMP")) {
                        valor = 20.0 + aleatorio.nextDouble() * 15.0;
                    } else if (idSensor.startsWith("PRESS")) {
                        valor = 1013.0 + aleatorio.nextDouble() * 30.0;
                    } else {
                        valor = 40.0 + aleatorio.nextDouble() * 30.0;
                    }
                    return new DadoSensor(idSensor, valor, System.currentTimeMillis());
                });
    }

    public Flux<DadoSensor> fluxoRafagas(int totalEventos, int tamanhoRajada, long atrasoEntreRajadasMillis) {
        return Flux.generate(() -> 0, (contador, sink) -> {
            if (contador >= totalEventos) {
                sink.complete();
                return contador;
            }
            int inicio = contador;
            int fim = Math.min(contador + tamanhoRajada, totalEventos);

            for (int i = inicio; i < fim; i++) {
                String idSensor = idsSensores.get(new Random().nextInt(idsSensores.size()));
                double valor = 50.0 + new Random().nextDouble() * 50.0;
                sink.next(new DadoSensor(idSensor, valor, System.currentTimeMillis()));
            }

            try {
                Thread.sleep(atrasoEntreRajadasMillis);
            } catch (InterruptedException e) {
                sink.error(e);
            }
            return fim;
        });
    }
}