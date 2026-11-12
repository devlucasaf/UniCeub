class ContaBancaria: ProtocoloOperacoesFinanceiras, ProtocoloDescricaoDetalhada {

    private static var proximoNumeroDeContaDisponivel: Int = 1001

    let numeroDaConta: Int
    let titularDaConta: Cliente

    private(set) var saldoAtual: Double = 0
    private(set) var historicoDeTransacoes: [Transacao] = []

    var nomeDoTipoDeConta: String {
        return "Conta Bancária"
    }

    init(titularDaConta: Cliente, saldoInicial: Double) {
        self.numeroDaConta = ContaBancaria.proximoNumeroDeContaDisponivel
        self.titularDaConta = titularDaConta
        ContaBancaria.proximoNumeroDeContaDisponivel += 1

        if saldoInicial > 0 {
            executarMovimentacaoDeSaldo(
                tipoDaTransacao: .deposito,
                valorDaMovimentacao: saldoInicial,
                descricaoDaMovimentacao: "Depósito de abertura"
            )
        }
    }

    func depositar(valorDoDeposito: Double) throws {
        try validarValorPositivo(valorInformado: valorDoDeposito)

        executarMovimentacaoDeSaldo(
            tipoDaTransacao: .deposito,
            valorDaMovimentacao: valorDoDeposito,
            descricaoDaMovimentacao: "Depósito em conta"
        )
    }

    func sacar(valorDoSaque: Double) throws {
        try validarValorPositivo(valorInformado: valorDoSaque)

        let valorDisponivelParaSaque = calcularValorDisponivelParaSaque()

        guard valorDoSaque <= valorDisponivelParaSaque else {
            throw ErroOperacaoBancaria.saldoInsuficiente(
                valorDisponivel: valorDisponivelParaSaque,
                valorSolicitado: valorDoSaque
            )
        }

        executarMovimentacaoDeSaldo(
            tipoDaTransacao: .saque,
            valorDaMovimentacao: valorDoSaque,
            descricaoDaMovimentacao: "Saque em conta"
        )
    }

    func consultarSaldo() -> Double {
        return saldoAtual
    }

    func transferir(valorDaTransferencia: Double, contaDeDestino: ContaBancaria) throws {
        guard self !== contaDeDestino else {
            throw ErroOperacaoBancaria.transferenciaParaMesmaConta(numeroDaConta: numeroDaConta)
        }

        try sacar(valorDoSaque: valorDaTransferencia)
        try contaDeDestino.depositar(valorDoDeposito: valorDaTransferencia)
    }

    func calcularValorDisponivelParaSaque() -> Double {
        return saldoAtual
    }

    func aplicarRotinaMensal() {
        fatalError("A subclasse \(type(of: self)) precisa sobrescrever o método aplicarRotinaMensal().")
    }

    func executarMovimentacaoDeSaldo(tipoDaTransacao: TipoTransacao, valorDaMovimentacao: Double, descricaoDaMovimentacao: String) {
        if tipoDaTransacao.transacaoAumentaSaldo {
            saldoAtual += valorDaMovimentacao
        } else {
            saldoAtual -= valorDaMovimentacao
        }

        let novaTransacao = Transacao(
            tipoDaTransacao: tipoDaTransacao,
            valorDaTransacao: valorDaMovimentacao,
            saldoAposTransacao: saldoAtual,
            descricaoDaTransacao: descricaoDaMovimentacao
        )

        historicoDeTransacoes.append(novaTransacao)
    }

    private func validarValorPositivo(valorInformado: Double) throws {
        guard valorInformado > 0 else {
            throw ErroOperacaoBancaria.valorInvalido(valorInformado: valorInformado)
        }
    }

    func gerarDescricaoDetalhada() -> String {
        let saldoFormatado = FormatadorMonetario.formatarValorEmReais(valorMonetario: saldoAtual)
        return "[\(nomeDoTipoDeConta) nº \(numeroDaConta)] Titular: \(titularDaConta.nomeCompleto) | Saldo: \(saldoFormatado)"
    }

    func gerarExtratoCompleto() -> String {
        var linhasDoExtrato = ["Extrato da \(nomeDoTipoDeConta) nº \(numeroDaConta) - \(titularDaConta.nomeCompleto)"]

        for transacaoRegistrada in historicoDeTransacoes {
            linhasDoExtrato.append(transacaoRegistrada.gerarLinhaDoExtrato())
        }

        return linhasDoExtrato.joined(separator: "\n")
    }
}
