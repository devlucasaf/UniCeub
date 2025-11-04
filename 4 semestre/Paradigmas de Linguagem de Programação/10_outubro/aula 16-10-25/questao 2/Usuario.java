/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Usuario {
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String email;

    public Usuario(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public void atualizarCadastro(String endereco, String telefone, String email) {
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }
}
