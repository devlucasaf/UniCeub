package situacao4;

public class Carro {
    private int     ano;
    private String  marca;
    private Modelo  modelo;
    private String  cor;
    private String  placa;

    public Carro(int ano, String marca, Modelo modelo, String cor, String placa) {
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
    }

    public String getPlaca() { 
        return placa; 
    }
    
    public String getDescricao() {
        return ano + " " + marca + " " + modelo.getNome() + " (" + cor + ")";
    }
}