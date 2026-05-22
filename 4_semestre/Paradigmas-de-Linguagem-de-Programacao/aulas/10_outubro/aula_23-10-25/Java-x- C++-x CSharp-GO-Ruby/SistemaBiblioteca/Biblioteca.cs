using System;
using System.Collections.Generic;

namespace SistemaBiblioteca
{
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
}