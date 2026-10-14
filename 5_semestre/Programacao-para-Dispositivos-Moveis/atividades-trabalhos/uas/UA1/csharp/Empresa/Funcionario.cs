using System;
using System.Globalization;

namespace Empresa;

public abstract class Funcionario : Bonus
{
    public string       Id              { get; private set; }
    public string       Nome            { get; private set; }
    public string       Cpf             { get; private set; }
    public double       SalarioBase     { get; protected set; }
    public Departamento Departamento    { get; private set; }
    public DateTime     DataAdmissao    { get; private set; }
    public bool         Ativo           { get; private set; }

    protected Funcionario(string nome, string cpf, double salarioBase, Departamento departamento, DateTime dataAdmissao)
    {
        Id = Guid.NewGuid().ToString().Substring(0, 8);
        Nome = nome;
        Cpf = cpf;
        SalarioBase = salarioBase;
        Departamento = departamento;
        DataAdmissao = dataAdmissao;
        Ativo = true;
    }

    public abstract double CalcularSalarioTotal();

    public void Desativar() => Ativo = false;
    public void Ativar()    => Ativo = true;

    public void AumentarSalario(double percentual)
    {
        if (percentual > 0)
        {
            SalarioBase += SalarioBase * (percentual / 100);
        }
    }

    public override string ToString()
    {
        return $"{Id} | {Nome} | {Cpf} | R$ {SalarioBase:F2} | {Departamento} | Adm: {DataAdmissao:dd/MM/yyyy} | Ativo: {Ativo}";
    }
}