package situacao1;

public class Aluno {
    private String  nome;
    private int     matricula;
    private String  curso;

    public Aluno(String nome, int matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public void realizarMatricula(Disciplina d, String semestre) {
        Matricula m = new Matricula(this, d, semestre);
        System.out.println("Matrícula realizada para " + d.getNome() + " no semestre " + semestre);
    }

    public String getNome() { 
        return nome; 
    }

    public int getMatricula() { 
        return matricula; 
    }

    public String getCurso() {
        return curso; 
    }
}