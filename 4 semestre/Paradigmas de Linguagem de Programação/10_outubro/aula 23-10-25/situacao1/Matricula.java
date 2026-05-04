package situacao1;

public class Matricula {
    private Aluno       aluno;
    private Disciplina  disciplina;
    private String      semestre;
    private double      notaFinal;

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