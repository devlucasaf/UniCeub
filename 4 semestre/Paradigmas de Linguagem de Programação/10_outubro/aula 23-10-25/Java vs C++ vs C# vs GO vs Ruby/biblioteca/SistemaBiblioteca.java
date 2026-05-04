/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 9: Diferença entre Java vs C++ vs C# vs GO vs Ruby
Exemplo em Java - Sistema de Biblioteca
*/

package biblioteca;

import java.util.Scanner;

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