using System;

namespace ParadigmaReativoExemplo
{
    public class GeradorEventos
    {
        private Random random = new Random();

        public Evento Gerar()
        {
            int t = random.Next(5);
            if (t == 0)
            {
                return new Evento("MOEDA", 10);
            }
            
            if (t == 1)
            {
                return new Evento("INIMIGO", 20);
            }

            if (t == 2)
            {
                return new Evento("TESOURO", 50);
            }

            if (t == 3)
            {
                return new Evento("ARMADILHA", -15);
            }
            return new Evento("BONUS", 30);
        }
    }
}