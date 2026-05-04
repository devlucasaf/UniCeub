using System;

namespace ParadigmaOO
{
    public abstract class Espaco
    {
        private string _nome;

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
}