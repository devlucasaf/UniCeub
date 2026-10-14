using System;
using System.Collections.Generic;
using System.Linq;

namespace Empresa;

public class Desenvolvedor : Funcionario
{
    public NivelExperiencia Nivel       { get; private set; }
    public List<string>     Linguagens  { get; private set; }

    public Desenvolvedor(string nome, string cpf, double salarioBase, Departamento departamento,
                        DateTime dataAdmissao, NivelExperiencia nivel, List<string> linguagens)
        : base(nome, cpf, salarioBase, departamento, dataAdmissao)
    {
        Nivel = nivel;
        Linguagens = new List<string>(linguagens);
    }

    public void AdicionarLinguagem(string linguagem)
    {
        if (!Linguagens.Contains(linguagem))
        {
            Linguagens.Add(linguagem);
        }
    }

    public override double CalcularBonus()
    {
        return Nivel switch
        {
            NivelExperiencia.Estagiario     => SalarioBase * 0.05,
            NivelExperiencia.Junior         => SalarioBase * 0.08,
            NivelExperiencia.Pleno          => SalarioBase * 0.12,
            NivelExperiencia.Senior         => SalarioBase * 0.18,
            NivelExperiencia.Especialista   => SalarioBase * 0.25,
            _ => 0
        };
    }

    public override double CalcularSalarioTotal() => SalarioBase + CalcularBonus();

    public override string ToString()
    {
        return $"[DEV] {base.ToString()} | Nível: {Nivel} | Linguagens: {string.Join(", ", Linguagens)}";
    }
}