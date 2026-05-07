using System;
using System.Threading.Tasks;

namespace ParadigmaConcorrente
{
    class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine(" EXEMPLO DE PARADIGMA CONCORRENTE EM C# ");

            Gerenciador gerenciador = new Gerenciador();

            gerenciador.AdicionarProcessador(
                new Processador(1, "Processador-A"));

            gerenciador.AdicionarProcessador(
                new Processador(2, "Processador-B"));

            gerenciador.AdicionarProcessador(
                new Processador(3, "Processador-C"));

            gerenciador.AdicionarProcessador(
                new Processador(4, "Processador-D"));

            gerenciador.ExecutarComThreads();

            await gerenciador.ExecutarComTasks();

            gerenciador.ExecutarParallelFor();

            gerenciador.ExecutarParallelForEach();

            gerenciador.ExecutarComLock();

            await gerenciador.ExecutarComCancelamento();

            Console.WriteLine(" FIM DA EXECUÇÃO ");

            Console.WriteLine("\nPressione qualquer tecla para sair...");
            Console.ReadKey();
        }
    }
}