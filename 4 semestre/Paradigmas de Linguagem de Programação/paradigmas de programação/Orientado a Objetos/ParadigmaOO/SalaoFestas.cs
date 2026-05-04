using System;

namespace ParadigmaOO
{
    public class SalaoFestas : Espaco
    {
        public bool PermiteSomAlto { get; private set; }

        public SalaoFestas(string nome, int capacidade, bool permiteSomAlto) : base(nome, capacidade)
        {
            PermiteSomAlto = permiteSomAlto;
        }

        public override bool PodeReservar(DateTime inicio, DateTime fim)
        {
            if (!base.PodeReservar(inicio, fim))
            {
                return false;
            }
            return inicio.DayOfWeek != DayOfWeek.Monday;
        }
    }
}