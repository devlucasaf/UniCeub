package questao3;

public class ClienteBanco {
    private String  nome;
    private String  cpf;
    private int     numero;
    private String  senha;

    public ClienteBanco(String nome, String cpf, int numero, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.senha = senha;
    }

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
        } else {
            System.out.println("Senha incorreta!");
        }
    }
}