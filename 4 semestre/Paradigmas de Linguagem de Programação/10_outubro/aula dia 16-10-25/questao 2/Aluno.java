/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Aluno extends Usuario {
    private String curso;
    private String matricula;

    public Aluno(String nome, String endereco, String telefone, String email, String curso, String matricula) {
        super(nome, endereco, telefone, email);
        this.curso = curso;
        this.matricula = matricula;
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Curso: " + curso);
        System.out.println("Matrícula: " + matricula);
    }
}
