/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.List;

public class Obra {
    private String titulo;
    private String categoria;
    private String idioma;
    private List<Autor> autores;
    private Editora editora;
    private int anoPublicacao;
    private int edicao;

    public Obra(String titulo, String categoria, String idioma, List<Autor> autores, Editora editora, int anoPublicacao, int edicao) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.idioma = idioma;
        this.autores = autores;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.edicao = edicao;
    }

    public String getTitulo() {
        return titulo;
    }
}
