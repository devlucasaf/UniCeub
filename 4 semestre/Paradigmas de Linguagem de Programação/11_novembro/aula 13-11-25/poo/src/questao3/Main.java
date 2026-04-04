package questao3;

public class Main {
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