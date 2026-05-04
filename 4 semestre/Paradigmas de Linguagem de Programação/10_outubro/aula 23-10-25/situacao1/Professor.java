package situacao1;

public class Professor {
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