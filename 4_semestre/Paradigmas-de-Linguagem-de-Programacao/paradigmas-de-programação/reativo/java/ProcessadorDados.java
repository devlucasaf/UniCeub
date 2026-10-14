package reativo;

import reactor.core.publisher.Flux;

public class ProcessadorDados {
    public Flux<DadoProcessado> calcularMediaMovel(Flux<DadoSensor> origem, int tamanhoJanela) {
        return origem
                .groupBy(DadoSensor::getIdSensor)
                .flatMap(fluxoAgrupado -> fluxoAgrupado
                        .buffer(tamanhoJanela, 1)
                        .filter(buffer -> buffer.size() == tamanhoJanela)
                        .map(buffer -> {
                            double media = buffer.stream().mapToDouble(DadoSensor::getValor).average().orElse(0.0);
                            return new DadoProcessado(fluxoAgrupado.key(), media, tamanhoJanela);
                        })
                );
    }

    public Flux<Alerta> detectarAnomalias(Flux<DadoProcessado> fluxoProcessado) {
        return fluxoProcessado
                .filter(dp -> {
                    if (dp.idSensor.startsWith("TEMP")) {
                        return dp.mediaMovel > 30.0 || dp.mediaMovel < 10.0;
                    } else if (dp.idSensor.startsWith("PRESS")) {
                        return dp.mediaMovel > 1040.0 || dp.mediaMovel < 980.0;
                    } else {
                        return dp.mediaMovel > 70.0 || dp.mediaMovel < 20.0;
                    }
                })
                .map(dp -> {
                    Gravidade gravidade = Gravidade.AVISO;

                    if ((dp.idSensor.startsWith("TEMP") && dp.mediaMovel > 35.0) ||
                        (dp.idSensor.startsWith("PRESS") && dp.mediaMovel > 1060.0) ||
                        (dp.idSensor.startsWith("HUM") && dp.mediaMovel > 80.0)) {
                        gravidade = Gravidade.CRITICO;
                    }
                    return new Alerta("Anomalia detectada para " + dp.idSensor + ": média=" + dp.mediaMovel, gravidade);
                });
    }
}