/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Sala {
    private String numero;
    private String bloco;
    private int capacidade;

    public Sala(String numero, String bloco, int capacidade) {
        this.numero = numero;
        this.bloco = bloco;
        this.capacidade = capacidade;
    }

    public void exibirInformacoes() {
        System.out.println("Sala: " + bloco + " - " + numero + " | Capacidade: " + capacidade);
    }
}
