using System;

namespace Empresa;

public class Estagiario : Funcionario
{
    public int      CargaHoraria    { get; private set; }
    public string   Supervisor      { get; private set; }

    public Estagiario(string nome, string cpf, double salarioBase, Departamento departamento,
                    DateTime dataAdmissao, int cargaHoraria, string supervisor)
        : base(nome, cpf, salarioBase, departamento, dataAdmissao)
    {
        CargaHoraria = cargaHoraria;
        Supervisor = supervisor;
    }

    public override double CalcularBonus()          => SalarioBase * 0.02;
    public override double CalcularSalarioTotal()   => SalarioBase + CalcularBonus();

    public override string ToString()
    {
        return $"[EST] {base.ToString()} | Carga: {CargaHoraria}h | Supervisor: {Supervisor}";
    }
}