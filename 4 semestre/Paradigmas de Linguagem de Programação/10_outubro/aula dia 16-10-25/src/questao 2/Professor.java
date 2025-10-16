/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Professor extends Usuario {
    private String departamento;
    private String siape;

    public Professor(String nome, String endereco, String telefone, String email, String departamento, String siape) {
        super(nome, endereco, telefone, email);
        this.departamento = departamento;
        this.siape = siape;
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Departamento: " + departamento);
        System.out.println("SIAPE: " + siape);
    }
}
