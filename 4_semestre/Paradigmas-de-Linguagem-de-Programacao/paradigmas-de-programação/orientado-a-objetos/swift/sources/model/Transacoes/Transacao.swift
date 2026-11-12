struct Transacao {

    let tipoDaTransacao: TipoTransacao
    let valorDaTransacao: Double
    let saldoAposTransacao: Double
    let descricaoDaTransacao: String

    func gerarLinhaDoExtrato() -> String {
        let sinalDaOperacao = tipoDaTransacao.transacaoAumentaSaldo ? "+" : "-"
        let valorFormatado = FormatadorMonetario.formatarValorEmReais(valorMonetario: valorDaTransacao)
        let saldoFormatado = FormatadorMonetario.formatarValorEmReais(valorMonetario: saldoAposTransacao)

        return "   \(sinalDaOperacao) \(valorFormatado) | \(tipoDaTransacao.nomeParaExibicao): \(descricaoDaTransacao) | Saldo: \(saldoFormatado)"
    }
}
