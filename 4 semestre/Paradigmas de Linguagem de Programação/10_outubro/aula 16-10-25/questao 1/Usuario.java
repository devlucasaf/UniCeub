/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Usuario {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String tipo; 
    private int idUsuario;

    public Usuario(String nome, String endereco, String telefone, String email, String tipo, int idUsuario) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.idUsuario = idUsuario;
    }

    public void cadastrarUsuario() {
        System.out.println("Usuário " + nome + " cadastrado com sucesso!");
    }

    public void atualizarCadastro(String novoEndereco, String novoTelefone) {
        this.endereco = novoEndereco;
        this.telefone = novoTelefone;
        System.out.println("Cadastro atualizado com sucesso!");
    }

    public void consultarUsuario() {
        System.out.println("Usuário: " + nome + " | Tipo: " + tipo);
    }
}
