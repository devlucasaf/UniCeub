using System;

namespace SistemaBiblioteca
{
    class Livro
    {
        public string   Titulo      { get; private set; }
        public string   Autor       { get; private set; }
        public bool     Emprestado  { get; private set; }

        public Livro(string titulo, string autor)
        {
            Titulo      = titulo;
            Autor       = autor;
            Emprestado  = false;
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
}