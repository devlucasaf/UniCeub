using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace ParadigmaConcorrente
{
    class Gerenciador
    {
        private List<Processador> processadores;
        private object locker = new object();
        private int contadorCompartilhado = 0;

        public Gerenciador()
        {
            processadores = new List<Processador>();
        }

        public void AdicionarProcessador(Processador p)
        {
            processadores.Add(p);
        }

        public void ExecutarComThreads()
        {
            Console.WriteLine("\n========== EXECUÇÃO COM THREADS ==========\n");

            List<Thread> threads = new List<Thread>();

            foreach (var processador in processadores)
            {
                Thread t = new Thread(processador.ExecutarProcessamento);
                threads.Add(t);
                t.Start();
            }

            foreach (var t in threads)
            {
                t.Join();
            }

            Console.WriteLine("\nTodas as threads finalizaram.\n");
        }

        public async Task ExecutarComTasks()
        {
            Console.WriteLine("\n========== EXECUÇÃO COM TASKS ==========\n");

            List<Task> tarefas = new List<Task>();

            foreach (var processador in processadores)
            {
                tarefas.Add(processador.ExecutarProcessamentoAsync());
            }

            await Task.WhenAll(tarefas);

            Console.WriteLine("\nTodas as tasks finalizaram.\n");
        }

        public void ExecutarParallelFor()
        {
            Console.WriteLine("\n========== EXECUÇÃO COM PARALLEL FOR ==========\n");

            Parallel.For(0, processadores.Count, i =>
            {
                Console.WriteLine($"Parallel.For iniciando {processadores[i].Nome}");

                for (int etapa = 1; etapa <= 3; etapa++)
                {
                    Console.WriteLine($"{processadores[i].Nome} etapa {etapa}");
                    Thread.Sleep(300);
                }

                Console.WriteLine($"Parallel.For finalizando {processadores[i].Nome}");
            });

            Console.WriteLine("\nParallel.For concluído.\n");
        }

        public void ExecutarParallelForEach()
        {
            Console.WriteLine("\n========== EXECUÇÃO COM PARALLEL FOREACH ==========\n");

            Parallel.ForEach(processadores, processador =>
            {
                Console.WriteLine($"Processando {processador.Nome}");

                for (int i = 1; i <= 4; i++)
                {
                    Console.WriteLine($"{processador.Nome} -> operação {i}");
                    Thread.Sleep(250);
                }

                Console.WriteLine($"{processador.Nome} concluído.");
            });

            Console.WriteLine("\nParallel.ForEach concluído.\n");
        }

        public void ExecutarComLock()
        {
            Console.WriteLine("\n========== EXEMPLO DE LOCK ==========\n");

            List<Thread> threads = new List<Thread>();

            for (int i = 0; i < 5; i++)
            {
                Thread t = new Thread(IncrementarContador);
                threads.Add(t);
                t.Start();
            }

            foreach (var t in threads)
            {
                t.Join();
            }

            Console.WriteLine($"\nValor final do contador: {contadorCompartilhado}\n");
        }

        private void IncrementarContador()
        {
            for (int i = 0; i < 10; i++)
            {
                lock (locker)
                {
                    contadorCompartilhado++;

                    Console.WriteLine(
                        $"Thread {Thread.CurrentThread.ManagedThreadId} " +
                        $"incrementou contador para {contadorCompartilhado}"
                    );
                }

                Thread.Sleep(100);
            }
        }

        public async Task ExecutarComCancelamento()
        {
            Console.WriteLine("\n========== TASK COM CANCELAMENTO ==========\n");

            CancellationTokenSource cts = new CancellationTokenSource();

            Task tarefa = Task.Run(async () =>
            {
                for (int i = 1; i <= 20; i++)
                {
                    if (cts.Token.IsCancellationRequested)
                    {
                        Console.WriteLine("Tarefa cancelada.");
                        return;
                    }

                    Console.WriteLine($"Executando tarefa longa {i}");
                    await Task.Delay(200);
                }

            }, cts.Token);

            await Task.Delay(1500);

            Console.WriteLine("Solicitando cancelamento...");
            cts.Cancel();

            await tarefa;

            Console.WriteLine("Processo de cancelamento finalizado.\n");
        }
    }
}