package questao2;

public class FilmeComedia extends Filme {
    private int nivelComedia;

    public FilmeComedia(String nome, int anoLancamento, double duracao, int nivelComedia) {
        super(nome, "Comédia", anoLancamento, duracao);
        this.nivelComedia = nivelComedia;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Nível de Comédia: " + nivelComedia);
        System.out.println("Este é um filme cheio de comédia!");
    }
}