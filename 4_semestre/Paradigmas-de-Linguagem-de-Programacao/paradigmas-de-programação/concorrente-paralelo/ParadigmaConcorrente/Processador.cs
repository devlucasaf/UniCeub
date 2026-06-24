using System;
using System.Threading;
using System.Threading.Tasks;

namespace ParadigmaConcorrente
{
    class Processador
    {
        public int      Id { get; set; }
        public string   Nome { get; set; }

        public Processador(int id, string nome)
        {
            Id = id;
            Nome = nome;
        }

        public void ExecutarProcessamento()
        {
            Console.WriteLine($"[Thread {Thread.CurrentThread.ManagedThreadId}] " +
                                $"Processador {Nome} iniciado.");

            for (int i = 1; i <= 5; i++)
            {
                Console.WriteLine($"Processador {Nome} executando etapa {i}");
                Thread.Sleep(500);
            }

            Console.WriteLine($"[Thread {Thread.CurrentThread.ManagedThreadId}] " +
                                $"Processador {Nome} finalizado.");
        }

        public async Task ExecutarProcessamentoAsync()
        {
            Console.WriteLine($"[Task] {Nome} iniciou processamento assíncrono.");

            for (int i = 1; i <= 5; i++)
            {
                Console.WriteLine($"[Task] {Nome} processando etapa {i}");
                await Task.Delay(400);
            }

            Console.WriteLine($"[Task] {Nome} finalizou processamento.");
        }
    }
}