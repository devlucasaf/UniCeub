/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 28: Linguagem OO
*/


using System;

class Retangulo
{
    public float Altura { get; set; }
    public float Base { get; set; }
    public float Area { get; private set; }

    public void CalculaArea()
    {
        Area = Altura * Base;
    }
}

class Program
{
    static void Main(string[] args)
    {
        Retangulo ret1 = new Retangulo();

        ret1.Altura = 10;
        ret1.Base = 10;

        ret1.CalculaArea();

        Console.WriteLine("Área do retângulo: " + ret1.Area);
    }
}
