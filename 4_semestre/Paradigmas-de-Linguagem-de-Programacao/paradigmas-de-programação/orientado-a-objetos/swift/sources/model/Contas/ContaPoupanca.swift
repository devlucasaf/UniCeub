final class ContaPoupanca: ContaBancaria {

    let taxaDeRendimentoMensal: Double
    let quantidadeMaximaDeSaquesPorMes: Int = 3

    private(set) var quantidadeDeSaquesRealizadosNoMes: Int = 0

    override var nomeDoTipoDeConta: String {
        return "Conta Poupança"
    }

    init(titularDaConta: Cliente, saldoInicial: Double, taxaDeRendimentoMensal: Double) {
        self.taxaDeRendimentoMensal = taxaDeRendimentoMensal
        super.init(titularDaConta: titularDaConta, saldoInicial: saldoInicial)
    }

    override func sacar(valorDoSaque: Double) throws {
        guard quantidadeDeSaquesRealizadosNoMes < quantidadeMaximaDeSaquesPorMes else {
            throw ErroOperacaoBancaria.limiteDeSaquesAtingido(quantidadeMaximaDeSaques: quantidadeMaximaDeSaquesPorMes)
        }

        try super.sacar(valorDoSaque: valorDoSaque)
        quantidadeDeSaquesRealizadosNoMes += 1
    }

    override func aplicarRotinaMensal() {
        let valorDoRendimento = consultarSaldo() * taxaDeRendimentoMensal

        if valorDoRendimento > 0 {
            let taxaEmPercentual = taxaDeRendimentoMensal * 100
            executarMovimentacaoDeSaldo(
                tipoDaTransacao: .rendimento,
                valorDaMovimentacao: valorDoRendimento,
                descricaoDaMovimentacao: "Rendimento mensal de \(taxaEmPercentual)%"
            )
        }

        quantidadeDeSaquesRealizadosNoMes = 0
    }

    override func gerarDescricaoDetalhada() -> String {
        return super.gerarDescricaoDetalhada() + " | Saques no mês: \(quantidadeDeSaquesRealizadosNoMes)/\(quantidadeMaximaDeSaquesPorMes)"
    }
}
