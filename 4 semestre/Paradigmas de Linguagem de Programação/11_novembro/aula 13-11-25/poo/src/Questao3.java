class ClienteBanco {
    private String nome;
    private String cpf;
    private int numero;
    private String senha;

    public ClienteBanco(String nome, String cpf, int numero, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.senha = senha;
    }

    // Método Get
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    // Método Set
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Função para exibir os dados do cliente
    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Número celular: " + numero);
        System.out.println("Senha: " + senha);
    }

    public boolean autenticarCadastro(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public void alterarSenha(String senhaAntiga, String novaSenha) {
        if (autenticarCadastro(senhaAntiga)) {
            this.senha = novaSenha;
            System.out.println("Senha atualizada com sucesso!");
        }
        else {
            System.out.println("Senha incorreta!");
        }

    }

}
public class Questao3 {
    public static void main(String[] args) {
        ClienteBanco cliente1 = new ClienteBanco("Batista", "987654321115", 999999999, "fernando123");

        cliente1.mostrarDados();

        System.out.println("\nTentando alterar senha com senha errada:");
        cliente1.alterarSenha("fernando123", "FernandoGil123");

        System.out.println("\nTentando alterar senha com senha correta:");
        cliente1.alterarSenha("fernando123", "FernandoGil123");

        System.out.println("\nDados atualizados:");
        cliente1.mostrarDados();
    }
}
