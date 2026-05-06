package reativo;

import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DataSimulator {
    private final Random random = new Random();
    private final List<String> sensorIds = List.of("TEMP-01", "TEMP-02", "PRESS-01", "HUM-01");

    public Flux<SensorData> streamSensorData(long intervalMillis, int maxEvents) {
        return Flux.interval(Duration.ofMillis(intervalMillis))
                .take(maxEvents)
                .map(tick -> {
                    String sensorId = sensorIds.get(random.nextInt(sensorIds.size()));
                    double value;

                    if (sensorId.startsWith("TEMP")) {
                        value = 20.0 + random.nextDouble() * 15.0;
                    } else if (sensorId.startsWith("PRESS")) {
                        value = 1013.0 + random.nextDouble() * 30.0;
                    } else {
                        value = 40.0 + random.nextDouble() * 30.0;
                    }
                    return new SensorData(sensorId, value, System.currentTimeMillis());
                });
    }

    public Flux<SensorData> burstyStream(int totalEvents, int burstSize, long burstDelayMillis) {
        return Flux.generate(() -> 0, (counter, sink) -> {
            if (counter >= totalEvents) {
                sink.complete();
                return counter;
            }
            int start = counter;
            int end = Math.min(counter + burstSize, totalEvents);

            for (int i = start; i < end; i++) {
                String sensorId = sensorIds.get(new Random().nextInt(sensorIds.size()));
                double value = 50.0 + new Random().nextDouble() * 50.0;
                sink.next(new SensorData(sensorId, value, System.currentTimeMillis()));
            }

            try {
                Thread.sleep(burstDelayMillis);
            } catch (InterruptedException e) {
                sink.error(e);
            }
            return end;
        });
    }
}