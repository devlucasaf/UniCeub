package reativo;

import reactor.core.publisher.Flux;

public class DataProcessor {
    public Flux<ProcessedData> computeMovingAverage(Flux<SensorData> source, int windowSize) {
        return source
                .groupBy(SensorData::getSensorId)
                .flatMap(groupedFlux -> groupedFlux
                        .buffer(windowSize, 1)
                        .filter(buffer -> buffer.size() == windowSize)
                        .map(buffer -> {
                            double avg = buffer.stream().mapToDouble(SensorData::getValue).average().orElse(0.0);
                            return new ProcessedData(groupedFlux.key(), avg, windowSize);
                        })
                );
    }

    public Flux<Alert> detectAnomalies(Flux<ProcessedData> processedFlux) {
        return processedFlux
                .filter(pd -> {
                    if (pd.sensorId.startsWith("TEMP")) {
                        return pd.movingAverage > 30.0 || pd.movingAverage < 10.0;
                    } else if (pd.sensorId.startsWith("PRESS")) {
                        return pd.movingAverage > 1040.0 || pd.movingAverage < 980.0;
                    } else {
                        return pd.movingAverage > 70.0 || pd.movingAverage < 20.0;
                    }
                })
                .map(pd -> {
                    Alert.Severity severity = Alert.Severity.WARNING;

                    if ((pd.sensorId.startsWith("TEMP") && pd.movingAverage > 35.0) ||
                        (pd.sensorId.startsWith("PRESS") && pd.movingAverage > 1060.0) ||
                        (pd.sensorId.startsWith("HUM") && pd.movingAverage > 80.0)) {
                        severity = Alert.Severity.CRITICAL;
                    }
                    return new Alert("Anomaly detected for " + pd.sensorId + ": avg=" + pd.movingAverage, severity);
                });
    }
}