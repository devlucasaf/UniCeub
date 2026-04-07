package lojaexemplo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Loja loja = new Loja();

        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto");
            System.out.println("4 - Remover produto");
            System.out.println("5 - Valor total do estoque");
            System.out.println("6 - Produto mais caro");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Código: ");
                    int codigo = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();

                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();

                    Produto p = new Produto(codigo, nome, preco, quantidade);
                    loja.adicionarProduto(p);
                    break;

                case 2:
                    loja.listarProdutos();
                    break;

                case 3:
                    System.out.print("Código do produto: ");
                    int codBusca = sc.nextInt();

                    Produto encontrado = loja.buscarProduto(codBusca);

                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Código para remover: ");
                    int codRemove = sc.nextInt();
                    loja.removerProduto(codRemove);
                    break;

                case 5:
                    System.out.println("Total em estoque: " + loja.calcularValorTotalEstoque());
                    break;

                case 6:
                    Produto maisCaro = loja.produtoMaisCaro();
                    if (maisCaro != null) {
                        System.out.println("Produto mais caro:");
                        System.out.println(maisCaro);
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}