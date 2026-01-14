/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 9: Diferença entre Java vs C++ vs C#
Exemplo em Java - Sistema de Biblioteca
*/

import java.util.*;

class Livro {
    private String titulo;
    private String autor;
    private boolean emprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void emprestar() {
        emprestado = true;
    }

    public void devolver() {
        emprestado = false;
    }

    public String toString() {
        return titulo + " - " + autor + (emprestado ? " (Emprestado)" : " (Disponível)");
    }
}

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
            } 
            else {
                System.out.println("Livro já está emprestado.");
            }
        } 
        else {
            System.out.println("Índice inválido.");
        }
    }

    public void devolverLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            Livro livro = livros.get(indice);
            if (livro.isEmprestado()) {
                livro.devolver();
                System.out.println("Livro devolvido com sucesso.");
            } 
            else {
                System.out.println("Livro não está emprestado.");
            }
        } 
        else {
            System.out.println("Índice inválido.");
        }
    }
}

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Buscar por título");
            System.out.println("4. Emprestar livro");
            System.out.println("5. Devolver livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    biblioteca.adicionarLivro(titulo, autor);
                    break;
                case 2:
                    biblioteca.listarLivros();
                    break;
                case 3:
                    System.out.print("Digite o título: ");
                    String busca = scanner.nextLine();
                    biblioteca.buscarPorTitulo(busca);
                    break;
                case 4:
                    biblioteca.listarLivros();
                    System.out.print("Digite o número do livro para emprestar: ");
                    int emprestar = scanner.nextInt() - 1;
                    biblioteca.emprestarLivro(emprestar);
                    break;
                case 5:
                    biblioteca.listarLivros();
                    System.out.print("Digite o número do livro para devolver: ");
                    int devolver = scanner.nextInt() - 1;
                    biblioteca.devolverLivro(devolver);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
