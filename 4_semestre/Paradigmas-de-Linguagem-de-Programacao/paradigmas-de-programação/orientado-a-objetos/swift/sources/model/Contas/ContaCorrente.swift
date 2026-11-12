final class ContaCorrente: ContaBancaria {

    let valorDoLimiteChequeEspecial: Double
    let valorDaTarifaMensal: Double

    override var nomeDoTipoDeConta: String {
        return "Conta Corrente"
    }

    init(titularDaConta: Cliente, saldoInicial: Double, valorDoLimiteChequeEspecial: Double, valorDaTarifaMensal: Double) {
        self.valorDoLimiteChequeEspecial = valorDoLimiteChequeEspecial
        self.valorDaTarifaMensal = valorDaTarifaMensal
        super.init(titularDaConta: titularDaConta, saldoInicial: saldoInicial)
    }

    override func calcularValorDisponivelParaSaque() -> Double {
        return consultarSaldo() + valorDoLimiteChequeEspecial
    }

    override func aplicarRotinaMensal() {
        executarMovimentacaoDeSaldo(
            tipoDaTransacao: .tarifa,
            valorDaMovimentacao: valorDaTarifaMensal,
            descricaoDaMovimentacao: "Tarifa mensal de manutenção"
        )
    }

    override func gerarDescricaoDetalhada() -> String {
        let limiteFormatado = FormatadorMonetario.formatarValorEmReais(valorMonetario: valorDoLimiteChequeEspecial)
        return super.gerarDescricaoDetalhada() + " | Cheque especial: \(limiteFormatado)"
    }
}
