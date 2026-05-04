// File: ReativoCS/Condominio.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace ParadigmaOO
{
    public class Condominio
    {
        private readonly List<Pessoa>   _pessoas;
        private readonly List<Espaco>   _espacos;
        private readonly List<Reserva>  _reservas;

        public event Action<Reserva> ReservaCriada;

        public IReadOnlyList<Pessoa> Pessoas => _pessoas;
        public IReadOnlyList<Espaco> Espacos => _espacos;
        public IReadOnlyList<Reserva> Reservas => _reservas;

        public Condominio()
        {
            _pessoas = new List<Pessoa>();
            _espacos = new List<Espaco>();
            _reservas = new List<Reserva>();
        }

        public void RegistrarPessoa(Pessoa pessoa)
        {
            if (pessoa == null)
            {
                throw new ArgumentNullException(nameof(pessoa));
            }
            _pessoas.Add(pessoa);
        }

        public void RegistrarEspaco(Espaco espaco)
        {
            if (espaco == null)
            {
                throw new ArgumentNullException(nameof(espaco));
            }
            _espacos.Add(espaco);
        }

        public Reserva CriarReserva(Morador morador, Espaco espaco, DateTime inicio, DateTime fim)
        {
            if (ExisteConflito(espaco, inicio, fim))
            {
                throw new InvalidOperationException("Conflito de horario");
            }

            var reserva = new Reserva(morador, espaco, inicio, fim);
            _reservas.Add(reserva);
            morador.AdicionarReserva(reserva);

            ReservaCriada?.Invoke(reserva);
            return reserva;
        }

        private bool ExisteConflito(Espaco espaco, DateTime inicio, DateTime fim)
        {
            return _reservas.Any(r =>
                r.EspacoNome == espaco.Nome &&
                inicio < r.Fim &&
                fim > r.Inicio
            );
        }

        public IEnumerable<Reserva> ListarReservasPorEspaco(string nomeEspaco)
        {
            return _reservas
                .Where(r => r.EspacoNome.Equals(nomeEspaco, StringComparison.OrdinalIgnoreCase))
                .OrderBy(r => r.Inicio)
                .ToList();
        }
    }
}