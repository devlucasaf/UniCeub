/**
Paradigmas de Linguagem de Programação
Aula: 13-11-25
II Atividade 4 (Pilares em Programação Orientada a Objetos)
Questão 4) Aplique em alguma linguagem de programação orientada a objetos:

4.1. agregação

4.2. composição
*/

import java.util.ArrayList;
import java.util.List;

// Classe Filme
class Filme {
    private String nome;
    private String genero;
    private int anoLancamento;
    private double duracao;

    public Filme(String nome, String genero, int anoLancamento, double duracao) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
    }

    // Método Get
    public String getNome() {
        return nome;
    }

    // Função para mostrar os valores
    public void mostrarDados() {
        System.out.println("- " + nome);
        System.out.println("- " + genero);
        System.out.println("- " + anoLancamento);
        System.out.println("- " + duracao + "h");
    }

}

class Sessao{
    private String horario;
    private String sala;
    private String numeroCadeira;

    public Sessao(String horario, String sala, String numeroCadeira) {
        this.horario = horario;
        this.sala = sala;
        this.numeroCadeira = numeroCadeira;
    }

    public void mostrarSessao() {
        System.out.println("Sessão: " + horario);
        System.out.println("Sala: " + sala);
        System.out.println("Número da cadeira: " + numeroCadeira);
    }
}

class Cinema {
    private String nome;
    private List<Filme> filmes;
    private List<Sessao> sessoes;

    public Cinema(String nome) {
        this.nome = nome;
        this.filmes = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }
    
    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }
    
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

public class Questao4 {
    public static void main(String[] args) {
        Filme filme1 = new Filme("De Volta para o Futuro","Ficção Científica", 1985, 1.56);
        Filme filme2 = new Filme("Avatar", "Aventura", 2009, 2.42);
        
        Cinema cinema = new Cinema("Kinoplex");
        
        cinema.adicionarFilme(filme1);
        cinema.adicionarFilme(filme2);
        
        cinema.criarSessao("14:00", "Sala 14", "P8");
        cinema.criarSessao("21:30", "Sala 3", "H5");
    
    }

}
