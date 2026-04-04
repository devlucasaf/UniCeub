package questao2;

public class FilmeAcao extends Filme {
    private int nivelAdrenalina;

    public FilmeAcao(String nome, int anoLancamento, double duracao, int nivelAdrenalina) {
        super(nome, "Ação", anoLancamento, duracao);
        this.nivelAdrenalina = nivelAdrenalina;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Nível de Adrenalina: " + nivelAdrenalina);
        System.out.println("Este é um filme cheio de ação!");
    }
}