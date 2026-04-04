package questao4;

public class Main {
    public static void main(String[] args) {
        Filme filme1 = new Filme("De Volta para o Futuro","Ficção Científica", 1985, 1.56);
        Filme filme2 = new Filme("Avatar", "Aventura", 2009, 2.42);

        Cinema cinema = new Cinema("Kinoplex");

        cinema.adicionarFilme(filme1);
        cinema.adicionarFilme(filme2);

        cinema.criarSessao("14:00", "Sala 14", "P8");
        cinema.criarSessao("21:30", "Sala 3", "H5");

        cinema.mostrarCinema();
    }
}