/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private Curso curso;
    private Sala sala;
    private List<Aluno> alunos;

    public Turma(String codigo, Curso curso, Sala sala) {
        this.codigo = codigo;
        this.curso = curso;
        this.sala = sala;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void listarAlunos() {
        System.out.println("Alunos da turma " + codigo + ":");
        for (Aluno a : alunos) {
            System.out.println("- " + a.getNome());
        }
    }
}
