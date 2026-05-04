package veiculo;

public class Carro {
    private String modelo;
    private Pessoa proprietario;

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public String getModelo() {
        return modelo;
    }
}