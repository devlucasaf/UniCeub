using System;

namespace ParadigmaReativoExemplo
{
    public class Evento
    {
        public string Tipo { get; set; }
        public int Valor { get; set; }

        public Evento(string tipo, int valor)
        {
            Tipo = tipo;
            Valor = valor;
        }

        public override string ToString()
        {
            return Tipo + " (" + Valor + ")";
        }
    }
}