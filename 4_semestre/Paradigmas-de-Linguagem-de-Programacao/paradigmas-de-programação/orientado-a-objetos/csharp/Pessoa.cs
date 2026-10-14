using System;

namespace ParadigmaOO
{
    public abstract class Pessoa : INotificavel
    {
        private readonly Guid   _id;
        private string          _nome;

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
}