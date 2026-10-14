using System;

class Desafio
{
    static void Main()
    {
        Console.WriteLine("=== Calculador de Média ===\n");

        Console.Write("Digite o valor da Nota 01: ");
        double nota1 = LerDouble();

        Console.Write("Digite o valor da Nota 02: ");
        double nota2 = LerDouble();

        double media = (nota1 + nota2) / 2;

        Console.WriteLine($"\nA média entre Nota 1 ({nota1}) e Nota 2 ({nota2}) é: {media:F2}");
    }

    static double LerDouble()
    {
        while (true)
        {
            string? entrada = Console.ReadLine();
            if (double.TryParse(entrada, System.Globalization.NumberStyles.Any, 
                System.Globalization.CultureInfo.InvariantCulture, out double valor))
            {
                return valor;
            }
            Console.Write("Valor inválido! Digite um número válido: ");
        }
    }
}