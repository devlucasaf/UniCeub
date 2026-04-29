// Program.cs (Main)
using System;
using System.Collections.Generic;
using Empresa;

class Program
{
    static void Main()
    {
        Empresa empresa = new("Tech Solutions Ltda", "12.345.678/0001-99");

        var linguagensAna = new List<string> { "Java", "Python" };
        Desenvolvedor ana = new("Ana Silva", "111.222.333-44", 7500.0,
            Departamento.Tecnologia, new DateTime(2022, 3, 10), NivelExperiencia.Pleno, linguagensAna);

        var linguagensCarlos = new List<string> { "JavaScript", "TypeScript", "React" };
        Desenvolvedor carlos = new("Carlos Souza", "222.333.444-55", 10500.0,
            Departamento.Tecnologia, new DateTime(2019, 8, 1), NivelExperiencia.Senior, linguagensCarlos);

        var linguagensJulia = new List<string> { "C#", ".NET" };
        Desenvolvedor julia = new("Julia Mendes", "333.444.555-66", 5200.0,
            Departamento.Tecnologia, new DateTime(2023, 6, 15), NivelExperiencia.Junior, linguagensJulia);

        Gerente roberto = new("Roberto Alves", "444.555.666-77", 15000.0,
            Departamento.Tecnologia, new DateTime(2015, 2, 20), 15.0);

        Gerente fernanda = new("Fernanda Lima", "555.666.777-88", 13500.0,
            Departamento.Vendas, new DateTime(2018, 11, 5), 12.0);

        Estagiario bruno = new("Bruno Rocha", "666.777.888-99", 1800.0,
            Departamento.Marketing, new DateTime(2024, 1, 10), 30, "Fernanda Lima");

        empresa.Contratar(ana);
        empresa.Contratar(carlos);
        empresa.Contratar(julia);
        empresa.Contratar(roberto);
        empresa.Contratar(fernanda);
        empresa.Contratar(bruno);

        roberto.AdicionarSubordinado(ana);
        roberto.AdicionarSubordinado(carlos);
        roberto.AdicionarSubordinado(julia);
        fernanda.AdicionarSubordinado(bruno);

        Console.WriteLine("=== LISTA DE FUNCIONÁRIOS ===");
        foreach (var f in empresa.GetFuncionariosAtivos())
        {
            Console.WriteLine(f);
        }

        Console.WriteLine("\n=== TOTAL DA FOLHA MENSAL ===");
        Console.WriteLine($"R$ {empresa.CalcularTotalFolha():F2}");

        Console.WriteLine("\n=== DESENVOLVEDORES QUE USAM JAVA ===");
        foreach (var dev in empresa.GetDesenvolvedoresPorLinguagem("Java"))
        {    
            Console.WriteLine(dev);
        }

        Console.WriteLine("\n=== FUNCIONÁRIOS DO DEPARTAMENTO TECNOLOGIA ===");
        foreach (var f in empresa.GetFuncionariosPorDepartamento(Departamento.Tecnologia))
        {
            Console.WriteLine(f);
        }

        Console.WriteLine("\n=== APLICANDO AUMENTO DE 5% ===");
        empresa.AplicarAumentoGlobal(5.0);
        foreach (var f in empresa.GetFuncionariosAtivos())
        {
            Console.WriteLine(f);
        }

        Console.WriteLine("\n=== PROMOVENDO JULIA ===");
        empresa.PromoverDesenvolvedor(julia.Id, NivelExperiencia.Pleno);

        Console.WriteLine("\n=== ORDENADOS POR SALÁRIO (DECRESCENTE) ===");
        empresa.ListarFuncionariosOrdenadosPorSalario();

        Console.WriteLine("\n=== PROCESSAMENTO DE PAGAMENTO ===");
        empresa.ProcessarPagamentoTodos();

        Console.WriteLine("\n=== BUSCA POR CPF ===");
        var encontrado = empresa.BuscarFuncionarioPorCpf("111.222.333-44");
        if (encontrado != null)
        {
            Console.WriteLine($"Encontrado: {encontrado}");
        }
        else
        {
            Console.WriteLine("Não encontrado");
        }

        Console.WriteLine("\n=== DEMITINDO UM FUNCIONÁRIO ===");
        empresa.Demitir(julia.Id);
        Console.WriteLine("Após demissão, ativos:");
        foreach (var f in empresa.GetFuncionariosAtivos())
        {
            Console.WriteLine(f);
        }

        Console.WriteLine("\n=== NOVA TENTATIVA DE PAGAR DEMITIDO (deve lançar exceção tratada internamente) ===");
        empresa.ProcessarPagamentoTodos();
    }
}