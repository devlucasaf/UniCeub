package situacao2;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void comprarIngresso(Evento e, int codigo, double valor, String assento) {
        Ingresso ingresso = new Ingresso(codigo, valor, assento, e, this);
        System.out.println("Ingresso comprado para o evento " + e.getNome() + " por " + nome);
    }

    public String getNome() { 
        return nome; 
    }
}