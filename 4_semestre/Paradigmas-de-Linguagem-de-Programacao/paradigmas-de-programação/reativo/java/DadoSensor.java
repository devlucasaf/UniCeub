package reativo;

public class DadoSensor {
    private final String    idSensor;
    private final double    valor;
    private final long      timestamp;

    public DadoSensor(String idSensor, double valor, long timestamp) {
        this.idSensor = idSensor;
        this.valor = valor;
        this.timestamp = timestamp;
    }

    public String getIdSensor() {
        return idSensor;
    }

    public double getValor() {
        return valor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DadoSensor{idSensor='" + idSensor + "', valor=" + valor + ", timestamp=" + timestamp + "}";
    }
}