class Filme {
    private String nome;
    private String genero;
    private int anoLancamento;
    private double duracao;

    public Filme(String nome, String genero, int anoLancamento, double duracao) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
    }

    public String getNome() { return nome; }
    public String getGenero() { return genero; }
    public int getAnoLancamento() { return anoLancamento; }
    public double getDuracao() { return duracao; }

    public void setNome(String nome) { this.nome = nome; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public void setDuracao(double duracao) { this.duracao = duracao; }

    public void mostrarDados() {
        System.out.println("Nome do filme: " + getNome());
        System.out.println("Gênero: " + getGenero());
        System.out.println("Ano de Lançamento: " + getAnoLancamento());
        System.out.println("Duração: " + getDuracao() + " horas");
    }
}

class FilmeAcao extends Filme {
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

class FilmeComedia extends Filme {
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

class FilmeTerror extends Filme {
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

class FilmeDrama extends Filme {
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

class FilmeFiccaoCientifica extends Filme {
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

public class MainFilme {
    public static void main(String[] args) {
        FilmeAcao filmeAcao1 = new FilmeAcao("O Contador", 2016, 2.6, 10);
        FilmeAcao filmeAcao2 = new FilmeAcao("Missão Impossível", 2023, 2.3, 10);
        FilmeComedia filmeComedia1 = new FilmeComedia("Minha Mãe é uma Peça", 2013, 1.24, 8);
        FilmeComedia filmeComedia2 = new FilmeComedia("Corra que a Polícia vem ai!", 1988, 1.25, 5);
        FilmeTerror filmeTerror1 = new FilmeTerror("Alien", 1979, 1.57, 8);
        FilmeTerror filmeTerror2 = new FilmeTerror("Invocação do Mal 2", 2016, 2.14, 9);
        FilmeDrama filmeDrama1 = new FilmeDrama("Caramelo", 2025, 1.40, 2);
        FilmeDrama filmeDrama2 = new FilmeDrama("Para Sempre Alice", 2014, 1.41, 7);
        FilmeFiccaoCientifica filmeFiccaoCientifica1 = new FilmeFiccaoCientifica("De Volta para o Futuro", 1985, 1.56, 6);
        FilmeFiccaoCientifica filmeFiccaoCientifica2 = new FilmeFiccaoCientifica("Avatar", 2009, 2.42, 10);

        Filme[] lista = {filmeAcao1, filmeAcao2,
                filmeComedia1, filmeComedia2,
                filmeTerror1, filmeTerror2,
                filmeDrama1, filmeDrama2,
                filmeFiccaoCientifica1, filmeFiccaoCientifica2
        };

        for (Filme f : lista) {
            System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
            f.mostrarDados();
        }
    }
}
