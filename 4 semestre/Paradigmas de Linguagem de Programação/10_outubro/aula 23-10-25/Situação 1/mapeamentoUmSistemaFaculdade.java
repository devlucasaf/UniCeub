/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
*/

class Aluno {
    private String nome;
    private int matricula;
    private String curso;
    public void realizarMatricula(Disciplina d, String semestre) { /* ... */ }
}

class Professor {
    private String nome, cpf, departamento;
    public void atribuirNota(Aluno a, Disciplina d, double nota) { /* ... */ }
}

class Disciplina {
    private String codigo, nome;
    private int cargaHoraria;
    public void exibirPlanoAula() { /* ... */ }
}

class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private String semestre;
    private double notaFinal;
    public double calcularMedia() { /* ... */ return notaFinal; }
}
