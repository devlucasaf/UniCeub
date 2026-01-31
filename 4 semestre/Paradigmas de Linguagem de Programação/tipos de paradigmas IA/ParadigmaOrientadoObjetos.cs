using System;
using System.Collections.Generic;
using System.Linq;

namespace ParadigmaOO
{
    public interface INotificavel
    {
        void Notificar(string mensagem);
    }

    public abstract class Pessoa : INotificavel
    {
        private readonly Guid _id;
        private string _nome;

        public Guid Id => _id;

        public string Nome
        {
            get => _nome;
            protected set
            {
                if (string.IsNullOrWhiteSpace(value)) 
                {
                    throw new ArgumentException("Nome invalido");
                }
                _nome = value.Trim();
            }
        }

        protected Pessoa(string nome)
        {
            _id = Guid.NewGuid();
            Nome = nome;
        }

        public abstract void Notificar(string mensagem);

        public override string ToString()
        {
            return $"{GetType().Name}({Nome})";
        }
    }

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
            if (reserva == null) throw new ArgumentNullException(nameof(reserva));
            _reservas.Add(reserva);
        }

        public override void Notificar(string mensagem)
        {
            Console.WriteLine($"[MORADOR] {Nome} <- {mensagem}");
        }
    }

    public class Funcionario : Pessoa
    {
        public string Cargo 
        { 
            get; 
            private set; 
        }

        public Funcionario(string nome, string cargo) : base(nome)
        {
            Cargo = string.IsNullOrWhiteSpace(cargo) ? "Funcionario" : cargo.Trim();
        }

        public override void Notificar(string mensagem)
        {
            Console.WriteLine($"[FUNCIONARIO] {Nome} <- {mensagem}");
        }
    }

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

    public abstract class Espaco
    {
        private string _nome;

        public string Nome
        {
            get => _nome;
            protected set
            {
                if (string.IsNullOrWhiteSpace(value)) throw new ArgumentException("Nome invalido");
                _nome = value.Trim();
            }
        }

        public int Capacidade { get; protected set; }

        protected Espaco(string nome, int capacidade)
        {
            Nome = nome;
            Capacidade = Math.Max(1, capacidade);
        }

        public virtual bool PodeReservar(DateTime inicio, DateTime fim)
        {
            return inicio < fim && inicio >= DateTime.Today;
        }

        public override string ToString()
        {
            return $"{GetType().Name}({Nome}, cap={Capacidade})";
        }
    }

    public class SalaoFestas : Espaco
    {
        public bool PermiteSomAlto 
        { 
            get; 
            private set; 
        }

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

    public class Churrasqueira : Espaco
    {
        public bool TemGrelha 
        { 
            get; 
            private set; 
        }

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

    public class Reserva
    {
        public Guid Id 
        { 
            get; 
        }
        public Guid MoradorId 
        { 
            get; 
        }
        public string MoradorNome 
        { 
            get; 
        }
        public string EspacoNome 
        { 
            get; 
        }
        public DateTime Inicio 
        { 
            get; 
        }
        public DateTime Fim 
        { 
            get; 
        }

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

    public class Condominio
    {
        private readonly List<Pessoa> _pessoas;
        private readonly List<Espaco> _espacos;
        private readonly List<Reserva> _reservas;

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

    public static class ParadigmaOrientadoObjetos
    {
        public static void Main()
        {
            var condominio = new Condominio();

            var sindico = new Sindico("Lucas");
            var porteiro = new Funcionario("Marcos", "Porteiro");
            var morador1 = new Morador("Ana", "A-101");
            var morador2 = new Morador("Bruno", "B-202");

            condominio.RegistrarPessoa(sindico);
            condominio.RegistrarPessoa(porteiro);
            condominio.RegistrarPessoa(morador1);
            condominio.RegistrarPessoa(morador2);

            var salao = new SalaoFestas("Salao Principal", 80, true);
            var churras = new Churrasqueira("Churrasqueira 1", 20, true);

            condominio.RegistrarEspaco(salao);
            condominio.RegistrarEspaco(churras);

            condominio.ReservaCriada += reserva =>
            {
                foreach (var p in condominio.Pessoas)
                {
                    p.Notificar($"Reserva criada: {reserva}");
                }
            };

            var inicio1 = DateTime.Today.AddDays(2).AddHours(18);
            var fim1 = DateTime.Today.AddDays(2).AddHours(22);

            var inicio2 = DateTime.Today.AddDays(3).AddHours(12);
            var fim2 = DateTime.Today.AddDays(3).AddHours(16);

            condominio.CriarReserva(morador1, salao, inicio1, fim1);
            condominio.CriarReserva(morador2, churras, inicio2, fim2);

            Console.WriteLine();
            Console.WriteLine("Reservas do Salao Principal:");
            foreach (var r in condominio.ListarReservasPorEspaco("Salao Principal"))
            {
                Console.WriteLine(r);
            }

            Console.WriteLine();
            Console.WriteLine("Reservas da Churrasqueira 1:");
            foreach (var r in condominio.ListarReservasPorEspaco("Churrasqueira 1"))
            {
                Console.WriteLine(r);
            }

            Console.WriteLine();
            Console.WriteLine("Resumo por pessoa:");
            foreach (var p in condominio.Pessoas)
            {
                Console.WriteLine(p.ToString());
            }
        }
    }
}
