package questao2;

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

    public String getGenero() {
        return genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public void mostrarDados() {
        System.out.println("Nome do filme: " + getNome());
        System.out.println("Gênero: " + getGenero());
        System.out.println("Ano de Lançamento: " + getAnoLancamento());
        System.out.println("Duração: " + getDuracao() + " horas");
    }
}