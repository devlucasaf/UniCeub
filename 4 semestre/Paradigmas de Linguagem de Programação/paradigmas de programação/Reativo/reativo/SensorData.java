package reativo;

public class SensorData {
    private final String    sensorId;
    private final double    value;
    private final long      timestamp;

    public SensorData(String sensorId, double value, long timestamp) {
        this.sensorId = sensorId;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SensorData{sensorId='" + sensorId + "', value=" + value + ", timestamp=" + timestamp + "}";
    }
}