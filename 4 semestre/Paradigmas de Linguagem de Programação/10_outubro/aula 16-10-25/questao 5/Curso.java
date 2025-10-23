/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private String codigo;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunos;
    private Turma turma;

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.disciplinas = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void matricularAluno(Aluno aluno) {
        if (alunos.size() < 40) {
            alunos.add(aluno);
            aluno.matricularEmCurso(this);
        } else {
            System.out.println("Limite de 40 alunos atingido para o curso " + nome);
        }
    }

    public void listarDisciplinas() {
        System.out.println("Disciplinas do curso " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
