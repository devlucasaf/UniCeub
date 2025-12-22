/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// ===============================
// Classe Autor
// ===============================
public class Autor {
    private String nome;
    private String nacionalidade;
    private String dataNascimento;

    public Autor(String nome, String nacionalidade, String dataNascimento) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
    }

    public void cadastrarAutor() {
        System.out.println("Autor " + nome + " cadastrado!");
    }

    public void consultarAutor() {
        System.out.println("Autor: " + nome + " | Nacionalidade: " + nacionalidade);
    }
}
