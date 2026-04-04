package questao4;

public class Sessao {
    private String horario;
    private String sala;
    private String numeroCadeira;

    public Sessao(String horario, String sala, String numeroCadeira) {
        this.horario = horario;
        this.sala = sala;
        this.numeroCadeira = numeroCadeira;
    }

    public void mostrarSessao() {
        System.out.println("Sessão: " + horario);
        System.out.println("Sala: " + sala);
        System.out.println("Número da cadeira: " + numeroCadeira);
    }
}