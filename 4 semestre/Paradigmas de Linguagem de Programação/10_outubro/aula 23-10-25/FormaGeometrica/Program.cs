using System;

namespace FormaGeometrica
{
    class Program
    {
        static void Main(string[] args)
        {
            try 
            {
                Retangulo ret1 = new Retangulo(10, 10);
                Console.WriteLine(ret1.ToString());

                ret1.Base = 20;
                Console.WriteLine($"Nova área após alteração: {ret1.Area}");
            }
            
            catch (Exception ex)
            {
                Console.WriteLine($"Erro: {ex.Message}");
            }
        }
    }
}