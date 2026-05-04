package reativo;

public class ProcessedData {
    private final String    sensorId;
    private final double    movingAverage;
    private final int       windowSize;

    public ProcessedData(String sensorId, double movingAverage, int windowSize) {
        this.sensorId = sensorId;
        this.movingAverage = movingAverage;
        this.windowSize = windowSize;
    }

    @Override
    public String toString() {
        return "ProcessedData{sensorId='" + sensorId + "', movingAverage=" + movingAverage + ", windowSize=" + windowSize + "}";
    }
}