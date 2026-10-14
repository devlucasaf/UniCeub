package reativo;

public class DadoProcessado {
    public final String    idSensor;
    public final double    mediaMovel;
    public final int       tamanhoJanela;

    public DadoProcessado(String idSensor, double mediaMovel, int tamanhoJanela) {
        this.idSensor = idSensor;
        this.mediaMovel = mediaMovel;
        this.tamanhoJanela = tamanhoJanela;
    }

    @Override
    public String toString() {
        return "DadoProcessado{idSensor='" + idSensor + "', mediaMovel=" + mediaMovel + ", tamanhoJanela=" + tamanhoJanela + "}";
    }
}