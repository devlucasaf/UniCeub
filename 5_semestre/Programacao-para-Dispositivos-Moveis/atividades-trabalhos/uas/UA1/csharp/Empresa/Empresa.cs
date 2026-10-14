// Empresa.cs
using System;
using System.Collections.Generic;
using System.Linq;

namespace Empresa;

public class Empresa
{
    private readonly string             _nome;
    private readonly string             _cnpj;
    private readonly List<Funcionario>  _funcionarios = new();
    private readonly FolhaPagamento     _folha = new();

    public Empresa(string nome, string cnpj)
    {
        _nome = nome;
        _cnpj = cnpj;
    }

    public void Contratar(Funcionario f)
    {
        if (f != null && !_funcionarios.Contains(f))
        {
            _funcionarios.Add(f);
        }
    }

    public void Demitir(string id)
    {
        var func = _funcionarios.FirstOrDefault(f => f.Id == id);
        if (func != null)
        {
            func.Desativar();
            Console.WriteLine($"Funcionário {func.Nome} desligado.");
        }
    }

    public List<Funcionario> GetFuncionariosAtivos() =>
        _funcionarios.Where(f => f.Ativo).ToList();

    public List<Funcionario> GetFuncionariosPorDepartamento(Departamento d) =>
        _funcionarios.Where(f => f.Departamento == d && f.Ativo).ToList();

    public List<Desenvolvedor> GetDesenvolvedoresPorLinguagem(string linguagem) =>
        _funcionarios
            .Where(f => f is Desenvolvedor && f.Ativo)
            .Cast<Desenvolvedor>()
            .Where(dev => dev.Linguagens.Contains(linguagem))
            .ToList();

    public Funcionario? BuscarFuncionarioPorCpf(string cpf) =>
        _funcionarios.FirstOrDefault(f => f.Cpf == cpf);

    public void AplicarAumentoGlobal(double percentual)
    {
        foreach (var f in _funcionarios.Where(f => f.Ativo))
        {
            f.AumentarSalario(percentual);
        }
    }

    public double CalcularTotalFolha() =>
        _funcionarios.Where(f => f.Ativo).Sum(f => f.CalcularSalarioTotal());

    public void ListarFuncionariosOrdenadosPorSalario()
    {
        var ordenados = _funcionarios
            .Where(f => f.Ativo)
            .OrderByDescending(f => f.CalcularSalarioTotal());

        foreach (var f in ordenados)
        {
            Console.WriteLine(f);
        }
    }

    public void ProcessarPagamentoTodos()
    {
        Console.WriteLine("=== PROCESSANDO FOLHA ===");
        foreach (var f in GetFuncionariosAtivos())
        {
            try
            {
                _folha.ProcessarPagamento(f);
            }
            catch (FuncionarioInativoException ex)
            {
                Console.Error.WriteLine(ex.Message);
            }
        }
        Console.WriteLine($"Total pago: R$ {_folha.TotalPago():F2}");
    }

    public void PromoverDesenvolvedor(string id, NivelExperiencia novoNivel)
    {
        var dev = _funcionarios
            .FirstOrDefault(f => f.Id == id && f is Desenvolvedor && f.Ativo) as Desenvolvedor;

        if (dev != null)
        {
            dev.AumentarSalario(15);
            Console.WriteLine($"Desenvolvedor {dev.Nome} promovido de {dev.Nivel} para {novoNivel}");
        }
    }
}