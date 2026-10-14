using System;
using System.Collections.Generic;

namespace ParadigmaOO
{
    public class Morador : Pessoa
    {
        private readonly List<Reserva> _reservas;

        public IReadOnlyList<Reserva> Reservas => _reservas;

        public string Apartamento { get; private set; }

        public Morador(string nome, string apartamento) : base(nome)
        {
            Apartamento = string.IsNullOrWhiteSpace(apartamento) ? "N/A" : apartamento.Trim();
            _reservas = new List<Reserva>();
        }

        public void AdicionarReserva(Reserva reserva)
        {
            if (reserva == null)
            {
                throw new ArgumentNullException(nameof(reserva));
            }
            _reservas.Add(reserva);
        }

        public override void Notificar(string mensagem)
        {
            Console.WriteLine($"[MORADOR] {Nome} <- {mensagem}");
        }
    }
}