package reativo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

public class ComplexStreamPipeline {
    private final DataSimulator simulator = new DataSimulator();
    private final DataProcessor processor = new DataProcessor();

    public Flux<String> buildPipeline(int events, long intervalMs) {
        return simulator.streamSensorData(intervalMs, events)
                .doOnNext(data -> System.out.println("[RAW] " + data))
                .filter(data -> data.getValue() > 0)
                .map(data -> {
                    if (data.getSensorId().contains("TEMP")) {
                        return new SensorData(data.getSensorId(), data.getValue() * 1.8 + 32, data.getTimestamp());
                    }
                    return data;
                })
                .flatMap(data -> {
                    if (data.getSensorId().equals("PRESS-01")) {
                        return Mono.just(data).delayElement(Duration.ofMillis(10));
                    }
                    return Mono.just(data);
                })
                .transform(flux -> processor.computeMovingAverage(flux, 5))
                .transform(processor::detectAnomalies)
                .flatMap(alert -> {
                    if (alert.severity == Alert.Severity.CRITICAL) {
                        return Mono.just("CRITICAL: " + alert.message).delayElement(Duration.ofMillis(5));
                    } else {
                        return Mono.just("WARNING: " + alert.message);
                    }
                })
                .onErrorResume(e -> Mono.just("Recovered from error: " + e.getMessage()));
    }
}