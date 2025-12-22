/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// ===============================
// Classe Obra
// ===============================
public class Obra {
    private String titulo;
    private String categoria;
    private String idioma;
    private Autor autor;
    private Editora editora;
    private Midia midia;
    private int anoEdicao;

    public Obra(String titulo, String categoria, String idioma, Autor autor, Editora editora, Midia midia, int anoEdicao) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.idioma = idioma;
        this.autor = autor;
        this.editora = editora;
        this.midia = midia;
        this.anoEdicao = anoEdicao;
    }

    public void cadastrarObra() {
        System.out.println("Obra '" + titulo + "' cadastrada com sucesso!");
    }

    public void atualizarObra(String novaCategoria, String novoIdioma) {
        this.categoria = novaCategoria;
        this.idioma = novoIdioma;
        System.out.println("Obra atualizada!");
    }

    public void consultarObra() {
        System.out.println("Título: " + titulo + " | Categoria: " + categoria + " | Idioma: " + idioma);
    }
}
