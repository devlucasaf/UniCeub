public class Main {
    public static void main(String[] args) {

        Autor autor = new Autor("Machado de Assis", "Brasileiro", "21/06/1839");
        Editora editora = new Editora("Companhia das Letras", 1986, "São Paulo");
        Midia midia = new Midia("Física", "Capa dura");

        Obra obra = new Obra("Dom Casmurro", "Literatura", "Português", autor, editora, midia, 1899);
        Usuario usuario = new Usuario("Lucas Freitas", "Rua A, 123", "99999-9999", "lucas@email.com", "Aluno", 1);
        Exemplar exemplar = new Exemplar("EX001", obra, 1899, "disponível");

        Emprestimo emprestimo = new Emprestimo(1, usuario, exemplar);

        emprestimo.registrarEmprestimo();
        exemplar.verificarDisponibilidade();

        emprestimo.finalizarEmprestimo();
        exemplar.verificarDisponibilidade();
    }
}
