/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Editora {
    private String nome;
    private int anoFundacao;
    private String endereco;

    public Editora(String nome, int anoFundacao, String endereco) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.endereco = endereco;
    }

    public void cadastrarEditora() {
        System.out.println("Editora " + nome + " cadastrada com sucesso!");
    }

    public void consultarEditora() {
        System.out.println("Editora: " + nome + " | Fundada em: " + anoFundacao);
    }
}
