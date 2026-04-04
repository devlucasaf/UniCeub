package questao4;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String          nome;
    private List<Filme>     filmes;
    private List<Sessao>    sessoes;

    public Cinema(String nome) {
        this.nome = nome;
        this.filmes = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    // AGREGAÇÃO (Cinema recebe filmes já criados)
    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    // COMPOSIÇÃO (Sessão é criada dentro do Cinema)
    public void criarSessao(String horario, String sala, String numeroCadeira) {
        sessoes.add(new Sessao(horario, sala, numeroCadeira));
    }

    public void mostrarCinema() {
        System.out.println("\n>>> Cinema: " + nome + " <<<");

        System.out.println("\nFilmes em cartaz:");
        for (Filme f : filmes) {
            f.mostrarDados();
        }

        System.out.println("\nSessões:");
        for (Sessao s : sessoes) {
            s.mostrarSessao();
        }
    }
}