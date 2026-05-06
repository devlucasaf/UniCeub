using System;

namespace LojaExemplo
{
    class Program
    {
        static void Main(string[] args)
        {
            Loja loja = new Loja();
            int opcao;

            do
            {
                Console.WriteLine("\n===== MENU =====");
                Console.WriteLine("1 - Adicionar produto");
                Console.WriteLine("2 - Listar produtos");
                Console.WriteLine("3 - Buscar produto");
                Console.WriteLine("4 - Remover produto");
                Console.WriteLine("5 - Valor total do estoque");
                Console.WriteLine("6 - Produto mais caro");
                Console.WriteLine("0 - Sair");
                Console.Write("Escolha: ");

                opcao = int.Parse(Console.ReadLine());

                switch (opcao)
                {
                    case 1:
                        Console.Write("Código: ");
                        int codigo = int.Parse(Console.ReadLine());

                        Console.Write("Nome: ");
                        string nome = Console.ReadLine();

                        Console.Write("Preço: ");
                        double preco = double.Parse(Console.ReadLine());

                        Console.Write("Quantidade: ");
                        int quantidade = int.Parse(Console.ReadLine());

                        Produto p = new Produto(codigo, nome, preco, quantidade);
                        loja.AdicionarProduto(p);
                        break;

                    case 2:
                        loja.ListarProdutos();
                        break;

                    case 3:
                        Console.Write("Código do produto: ");
                        int codBusca = int.Parse(Console.ReadLine());

                        Produto encontrado = loja.BuscarProduto(codBusca);
                        if (encontrado != null)
                            Console.WriteLine(encontrado);
                        else
                            Console.WriteLine("Produto não encontrado.");
                        break;

                    case 4:
                        Console.Write("Código para remover: ");
                        int codRemove = int.Parse(Console.ReadLine());
                        loja.RemoverProduto(codRemove);
                        break;

                    case 5:
                        Console.WriteLine("Total em estoque: " + loja.CalcularValorTotalEstoque());
                        break;

                    case 6:
                        Produto maisCaro = loja.ProdutoMaisCaro();
                        if (maisCaro != null)
                        {
                            Console.WriteLine("Produto mais caro:");
                            Console.WriteLine(maisCaro);
                        }
                        break;

                    case 0:
                        Console.WriteLine("Encerrando...");
                        break;

                    default:
                        Console.WriteLine("Opção inválida!");
                        break;
                }

            } while (opcao != 0);
        }
    }
}