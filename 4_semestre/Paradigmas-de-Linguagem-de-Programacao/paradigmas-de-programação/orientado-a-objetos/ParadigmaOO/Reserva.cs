// File: ReativoCS/Reserva.cs
using System;

namespace ParadigmaOO
{
    public class Reserva
    {
        public Guid     Id          { get; }
        public Guid     MoradorId   { get; }
        public string   MoradorNome { get; }
        public string   EspacoNome  { get; }
        public DateTime Inicio      { get; }
        public DateTime Fim         { get; }

        public Reserva(Morador morador, Espaco espaco, DateTime inicio, DateTime fim)
        {
            if (morador == null)
            {
                throw new ArgumentNullException(nameof(morador));
            }

            if (espaco == null)
            {
                throw new ArgumentNullException(nameof(espaco));
            }

            if (!espaco.PodeReservar(inicio, fim))
            {
                throw new InvalidOperationException("Periodo invalido para reserva");
            }

            Id = Guid.NewGuid();
            MoradorId = morador.Id;
            MoradorNome = morador.Nome;
            EspacoNome = espaco.Nome;
            Inicio = inicio;
            Fim = fim;
        }

        public override string ToString()
        {
            return $"{EspacoNome} por {MoradorNome} de {Inicio:dd/MM HH:mm} ate {Fim:dd/MM HH:mm}";
        }
    }
}