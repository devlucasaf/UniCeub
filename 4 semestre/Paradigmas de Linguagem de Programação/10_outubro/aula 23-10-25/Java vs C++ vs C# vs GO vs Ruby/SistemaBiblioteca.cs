/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 9: Diferença entre Java vs C++ vs C# vs GO vs Ruby
Exemplo em C++ - Sistema de Biblioteca
*/

using System;
using System.Collections.Generic;

class Livro
{
    public string Titulo { get; private set; }
    public string Autor { get; private set; }
    public bool Emprestado { get; private set; }

    public Livro(string titulo, string autor)
    {
        Titulo = titulo;
        Autor = autor;
        Emprestado = false;
    }

    public void Emprestar()
    {
        Emprestado = true;
    }

    public void Devolver()
    {
        Emprestado = false;
    }

    public override string ToString()
    {
        return $"{Titulo} - {Autor}" + (Emprestado ? " (Emprestado)" : " (Disponível)");
    }
}

class Biblioteca
{
    private List<Livro> livros;

    public Biblioteca()
    {
        livros = new List<Livro>();
    }

    public void AdicionarLivro(string titulo, string autor)
    {
        livros.Add(new Livro(titulo, autor));
    }

    public void ListarLivros()
    {
        if (livros.Count == 0)
        {
            Console.WriteLine("Nenhum livro cadastrado.");
            return;
        }

        for (int i = 0; i < livros.Count; i++)
        {
            Console.WriteLine($"{i + 1}. {livros[i]}");
        }
    }

    public void BuscarPorTitulo(string titulo)
    {
        foreach (var livro in livros)
        {
            if (livro.Titulo.Equals(titulo, StringComparison.OrdinalIgnoreCase))
            {
                Console.WriteLine("Encontrado: " + livro);
                return;
            }
        }
        Console.WriteLine("Livro não encontrado.");
    }

    public void EmprestarLivro(int indice)
    {
        if (indice >= 0 && indice < livros.Count)
        {
            var livro = livros[indice];
            if (!livro.Emprestado)
            {
                livro.Emprestar();
                Console.WriteLine("Livro emprestado com sucesso.");
            }
            else
            {
                Console.WriteLine("Livro já está emprestado.");
            }
        }
        else
        {
            Console.WriteLine("Índice inválido.");
        }
    }

    public void DevolverLivro(int indice)
    {
        if (indice >= 0 && indice < livros.Count)
        {
            var livro = livros[indice];
            if (livro.Emprestado)
            {
                livro.Devolver();
                Console.WriteLine("Livro devolvido com sucesso.");
            }
            else
            {
                Console.WriteLine("Livro não está emprestado.");
            }
        }
        else
        {
            Console.WriteLine("Índice inválido.");
        }
    }
}

class SistemaBiblioteca
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
