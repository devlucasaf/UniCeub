package reativo;

public class Alerta {
    private final String     mensagem;
    private final Gravidade  gravidade;

    public Alerta(String mensagem, Gravidade gravidade) {
        this.mensagem = mensagem;
        this.gravidade = gravidade;
    }

    @Override
    public String toString() {
        return "Alerta{mensagem='" + mensagem + "', gravidade=" + gravidade + "}";
    }
}