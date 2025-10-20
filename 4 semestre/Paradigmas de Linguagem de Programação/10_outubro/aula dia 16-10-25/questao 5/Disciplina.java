/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Disciplina {
    private String nome;
    private String codigo;
    private Professor professor;

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public void atribuirProfessor(Professor professor) {
        this.professor = professor;
        professor.adicionarDisciplina(this);
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }
}
