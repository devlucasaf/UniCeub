using System;

namespace ParadigmaReativoExemplo
{
    public class Jogador
    {
        public string Nome { get; set; }
        public int Pontos { get; set; }

        public Jogador(string nome)
        {
            Nome = nome;
            Pontos = 0;
        }

        public void AdicionarPontos(int valor)
        {
            Pontos += valor;
        }

        public override string ToString()
        {
            return Nome + " - " + Pontos;
        }
    }
}