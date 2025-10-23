/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private List<Curso> cursos;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.cursos = new ArrayList<>();
    }

    public void matricularEmCurso(Curso curso) {
        cursos.add(curso);
    }

    public void listarCursos() {
        System.out.println("Cursos de " + nome + ":");
        for (Curso c : cursos) {
            System.out.println("- " + c.getNome());
        }
    }

    public String getNome() {
        return nome;
    }
}
