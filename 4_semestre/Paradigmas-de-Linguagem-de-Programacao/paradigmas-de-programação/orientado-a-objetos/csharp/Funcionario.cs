using System;

namespace ParadigmaOO
{
    public class Funcionario : Pessoa
    {
        public string Cargo { get; private set; }

        public Funcionario(string nome, string cargo) : base(nome)
        {
            Cargo = string.IsNullOrWhiteSpace(cargo) ? "Funcionario" : cargo.Trim();
        }

        public override void Notificar(string mensagem)
        {
            Console.WriteLine($"[FUNCIONARIO] {Nome} <- {mensagem}");
        }
    }
}