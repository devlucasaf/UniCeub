// File: ReativoCS/ParadigmaOrientadoObjetos.cs
using System;

namespace ParadigmaOO
{
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