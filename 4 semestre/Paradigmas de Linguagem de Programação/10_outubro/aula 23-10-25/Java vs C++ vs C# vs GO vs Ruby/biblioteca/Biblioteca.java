package biblioteca;

import java.util.ArrayList;
import java.util.List;

class Biblioteca {
    private List<Livro> livros;

    public Biblioteca() {
        livros = new ArrayList<>();
    }

    public void adicionarLivro(String titulo, String autor) {
        livros.add(new Livro(titulo, autor));
    }

    public void listarLivros() {
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + ". " + livros.get(i));
        }
    }

    public void buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Encontrado: " + livro);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public void emprestarLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            Livro livro = livros.get(indice);
            if (!livro.isEmprestado()) {
                livro.emprestar();
                System.out.println("Livro emprestado com sucesso.");
            } else {
                System.out.println("Livro já está emprestado.");
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void devolverLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            Livro livro = livros.get(indice);
            if (livro.isEmprestado()) {
                livro.devolver();
                System.out.println("Livro devolvido com sucesso.");
            } else {
                System.out.println("Livro não está emprestado.");
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }
}