// Gerente.cs
using System;
using System.Collections.Generic;

namespace Empresa;

public class Gerente : Funcionario
{
    private readonly List<Funcionario> _equipe = new();

    public double BonusGestao { get; set; }

    public Gerente(string nome, string cpf, double salarioBase, Departamento departamento,
                DateTime dataAdmissao, double bonusGestao)
        : base(nome, cpf, salarioBase, departamento, dataAdmissao)
    {
        BonusGestao = bonusGestao;
    }

    public void AdicionarSubordinado(Funcionario f)
    {
        if (f != null && !_equipe.Contains(f))
        {
            _equipe.Add(f);
        }
    }

    public void RemoverSubordinado(Funcionario f) => _equipe.Remove(f);
    public List<Funcionario> GetEquipe() => new(_equipe);

    public override double CalcularBonus()          => SalarioBase * (BonusGestao / 100) + (_equipe.Count * 100);
    public override double CalcularSalarioTotal()   => SalarioBase + CalcularBonus();

    public override string ToString()
    {
        return $"[GER] {base.ToString()} | Bônus Gestão: {BonusGestao}% | Equipe: {_equipe.Count}";
    }
}