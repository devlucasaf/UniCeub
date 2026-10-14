using System;

namespace ParadigmaOO
{
    public class Churrasqueira : Espaco
    {
        public bool TemGrelha { get; private set; }

        public Churrasqueira(string nome, int capacidade, bool temGrelha) : base(nome, capacidade)
        {
            TemGrelha = temGrelha;
        }

        public override bool PodeReservar(DateTime inicio, DateTime fim)
        {
            if (!base.PodeReservar(inicio, fim))
            {
                return false;
            }
            return fim.Subtract(inicio).TotalHours <= 6;
        }
    }
}