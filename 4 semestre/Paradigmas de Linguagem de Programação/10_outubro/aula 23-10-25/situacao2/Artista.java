package situacao2;

public class Artista {
    private String nome;
    private String genero;

    public Artista(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
    }

    public void apresentar() {
        System.out.println("Apresentação do artista " + nome + " - Gênero: " + genero);
    }

    public String getNome() { return nome; }
}