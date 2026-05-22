using System;

namespace SistemaBiblioteca
{
    class Program
    {
        static void Main(string[] args)
        {
            Biblioteca biblioteca = new Biblioteca();
            int opcao;

            do
            {
                Console.WriteLine("\n--- Menu ---");
                Console.WriteLine("1. Adicionar livro");
                Console.WriteLine("2. Listar livros");
                Console.WriteLine("3. Buscar por título");
                Console.WriteLine("4. Emprestar livro");
                Console.WriteLine("5. Devolver livro");
                Console.WriteLine("0. Sair");
                Console.Write("Escolha uma opção: ");

                if (!int.TryParse(Console.ReadLine(), out opcao))
                {
                    Console.WriteLine("Entrada inválida.");
                    continue;
                }

                switch (opcao)
                {
                    case 1:
                        Console.Write("Título: ");
                        string titulo = Console.ReadLine();
                        Console.Write("Autor: ");
                        string autor = Console.ReadLine();
                        biblioteca.AdicionarLivro(titulo, autor);
                        break;

                    case 2:
                        biblioteca.ListarLivros();
                        break;

                    case 3:
                        Console.Write("Digite o título: ");
                        string busca = Console.ReadLine();
                        biblioteca.BuscarPorTitulo(busca);
                        break;

                    case 4:
                        biblioteca.ListarLivros();
                        Console.Write("Digite o número do livro para emprestar: ");
                        if (int.TryParse(Console.ReadLine(), out int indiceEmprestimo))
                        {
                            biblioteca.EmprestarLivro(indiceEmprestimo - 1);
                        }
                        else
                        {
                            Console.WriteLine("Número inválido.");
                        }
                        break;

                    case 5:
                        biblioteca.ListarLivros();
                        Console.Write("Digite o número do livro para devolver: ");
                        if (int.TryParse(Console.ReadLine(), out int indiceDevolucao))
                        {
                            biblioteca.DevolverLivro(indiceDevolucao - 1);
                        }
                        else
                        {
                            Console.WriteLine("Número inválido.");
                        }
                        break;

                    case 0:
                        Console.WriteLine("Saindo...");
                        break;

                    default:
                        Console.WriteLine("Opção inválida.");
                        break;
                }
            } while (opcao != 0);
        }
    }
}