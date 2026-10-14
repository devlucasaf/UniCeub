using System;
using System.Collections.Generic;
using System.Linq;

namespace Empresa;

public class FolhaPagamento
{
    private readonly Dictionary<string, double> _pagamentosRealizados = new();

    public void ProcessarPagamento(Funcionario f)
    {
        if (!f.Ativo)
        {
            throw new FuncionarioInativoException($"Funcionário {f.Nome} está inativo.");
        }

        double valor = f.CalcularSalarioTotal();
        _pagamentosRealizados[f.Id] = valor;
        Console.WriteLine($"Pagamento de R$ {valor:F2} para {f.Nome}");
    }

    public double TotalPago()       => _pagamentosRealizados.Values.Sum();
    public void LimparRegistros()   => _pagamentosRealizados.Clear();
}