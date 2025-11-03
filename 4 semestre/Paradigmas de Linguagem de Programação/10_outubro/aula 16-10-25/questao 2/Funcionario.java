/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Funcionario extends Usuario {
    private String departamento;
    private String cargo;

    public Funcionario(String nome, String endereco, String telefone, String email, String departamento, String cargo) {
        super(nome, endereco, telefone, email);
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Departamento: " + departamento);
        System.out.println("Cargo: " + cargo);
    }
}
