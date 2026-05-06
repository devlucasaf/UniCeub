package questao4;

public class Filme {
    private String  nome;
    private String  genero;
    private int     anoLancamento;
    private double  duracao;

    public Filme(String nome, String genero, int anoLancamento, double duracao) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void mostrarDados() {
        System.out.println("- " + nome);
        System.out.println("- " + genero);
        System.out.println("- " + anoLancamento);
        System.out.println("- " + duracao + "h");
    }
}