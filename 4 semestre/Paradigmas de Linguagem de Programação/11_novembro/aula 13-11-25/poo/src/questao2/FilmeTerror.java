package questao2;

public class FilmeTerror extends Filme {
    private int nivelMedo;

    public FilmeTerror(String nome, int anoLancamento, double duracao, int nivelMedo) {
        super(nome, "Terror", anoLancamento, duracao);
        this.nivelMedo = nivelMedo;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Nível de Medo: " + nivelMedo);
        System.out.println("Este é um filme cheio de medo!");
    }
}