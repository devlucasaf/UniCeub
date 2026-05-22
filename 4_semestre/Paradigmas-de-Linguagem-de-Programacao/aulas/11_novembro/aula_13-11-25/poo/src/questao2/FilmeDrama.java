package questao2;

public class FilmeDrama extends Filme {
    private int nivelTristeza;

    public FilmeDrama(String nome, int anoLancamento, double duracao, int nivelTristeza) {
        super(nome, "Drama", anoLancamento, duracao);
        this.nivelTristeza = nivelTristeza;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Nível de Tristeza: " + nivelTristeza);
        System.out.println("Este é um filme cheio de emoção e drama!");
    }
}