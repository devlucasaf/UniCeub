package poojava;

import java.util.*;

public class Matricula {
    private final UUID                      id;
    private final Aluno                     aluno;
    private final Curso                     curso;
    private final Map<String, Avaliacao>    avaliacoes;

    public Matricula(Aluno aluno, Curso curso) {
        this.id = UUID.randomUUID();
        this.aluno = Objects.requireNonNull(aluno);
        this.curso = Objects.requireNonNull(curso);
        this.avaliacoes = new LinkedHashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void adicionarAvaliacao(Avaliacao a) {
        if (a == null) {
            throw new IllegalArgumentException("Avaliação nula");
        }
        
        if (avaliacoes.containsKey(a.getNome())) {
            throw new IllegalArgumentException("Avaliação duplicada");
        }
        
        avaliacoes.put(a.getNome(), a);
    }

    public void setNota(String nomeAvaliacao, double nota) {
        Avaliacao a = avaliacoes.get(nomeAvaliacao);
        
        if (a == null) {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
        
        a.setNota(nota);
    }

    public double notaFinal() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        
        double soma = 0.0;
        
        for (Avaliacao a : avaliacoes.values()) {
            soma += a.notaNormalizada();
        }
        
        return soma / avaliacoes.size();
    }

    public String situacao() {
        double nota = notaFinal();
        
        if (avaliacoes.isEmpty()) {
            return "Sem notas";
        }
        
        return nota >= 6.0 ? "Aprovado" : "Reprovado";
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", aluno=" + aluno.getNome() + ", curso=" + curso.getCodigo() + ", final=" + notaFinal() + '}';
    }
}