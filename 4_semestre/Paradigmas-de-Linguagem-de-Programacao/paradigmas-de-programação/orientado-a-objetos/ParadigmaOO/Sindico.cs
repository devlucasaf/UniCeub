using System;

namespace ParadigmaOO
{
    public class Sindico : Funcionario
    {
        public Sindico(string nome) : base(nome, "Sindico")
        {
        }

        public override void Notificar(string mensagem)
        {
            Console.WriteLine($"[SINDICO] {Nome} <- {mensagem}");
        }
    }
}