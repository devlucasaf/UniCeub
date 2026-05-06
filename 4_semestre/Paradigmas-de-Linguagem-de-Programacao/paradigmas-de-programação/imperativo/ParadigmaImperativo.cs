using System;

namespace ParadigmaImperativo
{
    class Program
    {
        static void Main(string[] args)
        {
            int opcao = 0;

            while (opcao != 6)
            {
                Console.WriteLine("===== MENU =====");
                Console.WriteLine("1 - Somar dois números");
                Console.WriteLine("2 - Verificar número par ou ímpar");
                Console.WriteLine("3 - Calcular fatorial");
                Console.WriteLine("4 - Tabuada");
                Console.WriteLine("5 - Ordenar vetor");
                Console.WriteLine("6 - Sair");
                Console.Write("Escolha: ");

                opcao = int.Parse(Console.ReadLine());

                if (opcao == 1)
                {
                    int a, b, soma;
                    Console.Write("Digite o primeiro número: ");
                    a = int.Parse(Console.ReadLine());
                    Console.Write("Digite o segundo número: ");
                    b = int.Parse(Console.ReadLine());
                    soma = a + b;
                    Console.WriteLine("Resultado: " + soma);
                }

                else if (opcao == 2)
                {
                    int numero;
                    Console.Write("Digite um número: ");
                    numero = int.Parse(Console.ReadLine());

                    if (numero % 2 == 0)
                    {
                        Console.WriteLine("Número par");
                    }

                    else
                    {
                        Console.WriteLine("Número ímpar");
                    }
                }

                else if (opcao == 3)
                {
                    int n, fatorial = 1;
                    Console.Write("Digite um número: ");
                    n = int.Parse(Console.ReadLine());

                    for (int i = 1; i <= n; i++)
                    {
                        fatorial = fatorial * i;
                    }

                    Console.WriteLine("Fatorial: " + fatorial);
                }

                else if (opcao == 4)
                {
                    int numero;
                    Console.Write("Digite um número: ");
                    numero = int.Parse(Console.ReadLine());

                    for (int i = 1; i <= 10; i++)
                    {
                        Console.WriteLine(numero + " x " + i + " = " + (numero * i));
                    }
                }

                else if (opcao == 5)
                {
                    int tamanho;
                    Console.Write("Digite o tamanho do vetor: ");
                    tamanho = int.Parse(Console.ReadLine());

                    int[] vetor = new int[tamanho];

                    for (int i = 0; i < tamanho; i++)
                    {
                        Console.Write("Digite o valor " + i + ": ");
                        vetor[i] = int.Parse(Console.ReadLine());
                    }

                    for (int i = 0; i < tamanho - 1; i++)
                    {
                        for (int j = 0; j < tamanho - i - 1; j++)
                        {
                            if (vetor[j] > vetor[j + 1])
                            {
                                int temp = vetor[j];
                                vetor[j] = vetor[j + 1];
                                vetor[j + 1] = temp;
                            }
                        }
                    }

                    Console.WriteLine("Vetor ordenado:");
                    for (int i = 0; i < tamanho; i++) 
                    {
                        Console.Write(vetor[i] + " ");
                    }
                    Console.WriteLine();
                }

                else if (opcao == 6)
                {
                    Console.WriteLine("Encerrando...");
                }

                else
                {
                    Console.WriteLine("Opção inválida");
                }

                Console.WriteLine();
            }

            int contador = 0;

            while (contador < 10)
            {
                Console.WriteLine("Contador: " + contador);
                contador++;
            }

            int somaTotal = 0;

            for (int i = 1; i <= 50; i++)
            {
                somaTotal += i;
            }

            Console.WriteLine("Soma de 1 a 50: " + somaTotal);

            int[] numeros = new int[20];

            for (int i = 0; i < numeros.Length; i++)
            {
                numeros[i] = i * 2;
            }

            for (int i = 0; i < numeros.Length; i++)
            {
                Console.WriteLine("Valor: " + numeros[i]);
            }

            int maior = numeros[0];

            for (int i = 1; i < numeros.Length; i++)
            {
                if (numeros[i] > maior)
                {
                    maior = numeros[i];
                }
            }

            Console.WriteLine("Maior valor: " + maior);

            int menor = numeros[0];

            for (int i = 1; i < numeros.Length; i++)
            {
                if (numeros[i] < menor)
                {
                    menor = numeros[i];
                }
            }

            Console.WriteLine("Menor valor: " + menor);

            int media = 0;

            for (int i = 0; i < numeros.Length; i++)
            {
                media += numeros[i];
            }

            media = media / numeros.Length;

            Console.WriteLine("Média: " + media);

            int busca = 10;
            bool encontrado = false;

            for (int i = 0; i < numeros.Length; i++)
            {
                if (numeros[i] == busca)
                {
                    encontrado = true;
                }
            }

            if (encontrado)
            {
                Console.WriteLine("Número encontrado");
            }

            else
            {
                Console.WriteLine("Número não encontrado");
            }

            int acumulador = 0;

            for (int i = 0; i < 100; i++)
            {
                acumulador += i;
            }

            Console.WriteLine("Acumulador: " + acumulador);

            int x = 0;

            do
            {
                Console.WriteLine("Valor de x: " + x);
                x++;
            } 
            while (x < 5);

            int y = 10;

            while (y > 0)
            {
                Console.WriteLine("y: " + y);
                y--;
            }

            int z = 1;

            for (int i = 1; i <= 5; i++)
            {
                z = z * i;
            }

            Console.WriteLine("Multiplicação acumulada: " + z);

            int[] vetor2 = new int[10];

            for (int i = 0; i < vetor2.Length; i++)
            {
                vetor2[i] = vetor2.Length - i;
            }

            for (int i = 0; i < vetor2.Length; i++)
            {
                Console.WriteLine(vetor2[i]);
            }

            for (int i = 0; i < vetor2.Length - 1; i++)
            {
                for (int j = 0; j < vetor2.Length - i - 1; j++)
                {
                    if (vetor2[j] > vetor2[j + 1])
                    {
                        int temp = vetor2[j];
                        vetor2[j] = vetor2[j + 1];
                        vetor2[j + 1] = temp;
                    }
                }
            }

            Console.WriteLine("Ordenado novamente:");

            for (int i = 0; i < vetor2.Length; i++)
            {
                Console.WriteLine(vetor2[i]);
            }
        }
    }
}