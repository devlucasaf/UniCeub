package questao2;

public class FilmeFiccaoCientifica extends Filme {
    private int nivelFiccao;

    public FilmeFiccaoCientifica(String nome, int anoLancamento, double duracao, int nivelFiccao) {
        super(nome, "Ficção Científica", anoLancamento, duracao);
        this.nivelFiccao = nivelFiccao;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.println("Nível de Ficção: " + nivelFiccao);
        System.out.println("Este é um filme cheio de tecnologia e imaginação!");
    }
}