/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 1 - Sistema de Gestão de Matrículas Acadêmica
*/

class Aluno {
    private String nome;
    private int matricula;
    private String curso;

    public Aluno(String nome, int matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public void realizarMatricula(Disciplina d, String semestre) {
        Matricula m = new Matricula(this, d, semestre);
        System.out.println("Matrícula realizada para " + d.getNome() + " no semestre " + semestre);
    }

    public String getNome() { return nome; }
    public int getMatricula() { return matricula; }
    public String getCurso() { return curso; }
}

class Professor {
    private String nome;
    private String cpf;
    private String departamento;

    public Professor(String nome, String cpf, String departamento) {
        this.nome = nome;
        this.cpf = cpf;
        this.departamento = departamento;
    }

    public void atribuirNota(Aluno a, Disciplina d, double nota) {
        Matricula m = new Matricula(a, d, "2023.2");
        m.setNotaFinal(nota);
        System.out.println("Nota atribuída: " + nota + " para aluno " + a.getNome());
    }
}

class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;

    public Disciplina(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public void exibirPlanoAula() {
        System.out.println("Plano de aula da disciplina " + nome + ": Carga horária de " + cargaHoraria + " horas.");
    }

    public String getNome() { return nome; }
}

class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private String semestre;
    private double notaFinal;

    public Matricula(Aluno aluno, Disciplina disciplina, String semestre) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.semestre = semestre;
    }

    public double calcularMedia() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }
}

public class SistemaAcademico {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("Lucas", 123, "Computação");
        Disciplina disciplina = new Disciplina("POO101", "Programação Orientada a Objetos", 60);
        Professor professor = new Professor("Maria", "111.222.333-44", "Engenharia de Software");

        aluno.realizarMatricula(disciplina, "2023.2");
        disciplina.exibirPlanoAula();
        professor.atribuirNota(aluno, disciplina, 9.5);
    }
}
