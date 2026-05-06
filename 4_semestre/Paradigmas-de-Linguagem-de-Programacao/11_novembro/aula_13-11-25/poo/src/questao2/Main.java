package questao2;

public class Main {
    public static void main(String[] args) {
        // Filme de ação
        FilmeAcao filmeAcao1 = new FilmeAcao("O Contador", 2016, 2.6, 10);
        FilmeAcao filmeAcao2 = new FilmeAcao("Missão Impossível", 2023, 2.3, 10);

        // Filme de comédia
        FilmeComedia filmeComedia1 = new FilmeComedia("Minha Mãe é uma Peça", 2013, 1.24, 8);
        FilmeComedia filmeComedia2 = new FilmeComedia("Corra que a Polícia vem ai!", 1988, 1.25, 5);

        // Filme de terror
        FilmeTerror filmeTerror1 = new FilmeTerror("Alien", 1979, 1.57, 8);
        FilmeTerror filmeTerror2 = new FilmeTerror("Invocação do Mal 2", 2016, 2.14, 9);

        // Filme de drama
        FilmeDrama filmeDrama1 = new FilmeDrama("Caramelo", 2025, 1.40, 2);
        FilmeDrama filmeDrama2 = new FilmeDrama("Para Sempre Alice", 2014, 1.41, 7);

        // Filme de ficção científica
        FilmeFiccaoCientifica filmeFiccaoCientifica1 = new FilmeFiccaoCientifica("De Volta para o Futuro", 1985, 1.56, 6);
        FilmeFiccaoCientifica filmeFiccaoCientifica2 = new FilmeFiccaoCientifica("Avatar", 2009, 2.42, 10);

        Filme[] lista = {
                filmeAcao1, filmeAcao2,
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