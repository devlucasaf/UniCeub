public class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void exibir() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Email: " + this.email);
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Ana Souza", "ana@email.com");
        cliente.exibir();  
    }
}