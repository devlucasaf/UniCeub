final class Banco {

    let nomeDoBanco: String
    let gerenteResponsavel: Funcionario

    private var listaDeClientes: [Cliente] = []

    private var listaDeContas: [ContaBancaria] = []

    init(nomeDoBanco: String, gerenteResponsavel: Funcionario) {
        self.nomeDoBanco = nomeDoBanco
        self.gerenteResponsavel = gerenteResponsavel
    }

    func cadastrarCliente(nomeCompleto: String, numeroDoCpf: String, anoDeNascimento: Int) -> Cliente {
        let novoCliente = Cliente(
            nomeCompleto: nomeCompleto,
            numeroDoCpf: numeroDoCpf,
            anoDeNascimento: anoDeNascimento,
            codigoDoCliente: listaDeClientes.count + 1
        )

        listaDeClientes.append(novoCliente)
        return novoCliente
    }

    func abrirContaCorrente(titularDaConta: Cliente, saldoInicial: Double, valorDoLimiteChequeEspecial: Double, valorDaTarifaMensal: Double) -> ContaCorrente {
        let novaContaCorrente = ContaCorrente(
            titularDaConta: titularDaConta,
            saldoInicial: saldoInicial,
            valorDoLimiteChequeEspecial: valorDoLimiteChequeEspecial,
            valorDaTarifaMensal: valorDaTarifaMensal
        )

        listaDeContas.append(novaContaCorrente)
        return novaContaCorrente
    }

    func abrirContaPoupanca(titularDaConta: Cliente, saldoInicial: Double, taxaDeRendimentoMensal: Double) -> ContaPoupanca {
        let novaContaPoupanca = ContaPoupanca(
            titularDaConta: titularDaConta,
            saldoInicial: saldoInicial,
            taxaDeRendimentoMensal: taxaDeRendimentoMensal
        )

        listaDeContas.append(novaContaPoupanca)
        return novaContaPoupanca
    }

    func buscarConta(numeroDaConta: Int) throws -> ContaBancaria {
        guard let contaEncontrada = listaDeContas.first(where: { $0.numeroDaConta == numeroDaConta }) else {
            throw ErroOperacaoBancaria.contaNaoEncontrada(numeroDaConta: numeroDaConta)
        }

        return contaEncontrada
    }

    func transferirEntreContas(numeroDaContaDeOrigem: Int, numeroDaContaDeDestino: Int, valorDaTransferencia: Double) throws {
        let contaDeOrigem = try buscarConta(numeroDaConta: numeroDaContaDeOrigem)
        let contaDeDestino = try buscarConta(numeroDaConta: numeroDaContaDeDestino)

        try contaDeOrigem.transferir(valorDaTransferencia: valorDaTransferencia, contaDeDestino: contaDeDestino)
    }

    func listarContasDoCliente(clienteProcurado: Cliente) -> [ContaBancaria] {
        return listaDeContas.filter { $0.titularDaConta === clienteProcurado }
    }

    func calcularPatrimonioTotalDoCliente(clienteProcurado: Cliente) -> Double {
        return listarContasDoCliente(clienteProcurado: clienteProcurado)
            .reduce(0) { somaParcial, contaDoCliente in somaParcial + contaDoCliente.consultarSaldo() }
    }

    func obterTodasAsContas() -> [ContaBancaria] {
        return listaDeContas
    }

    func executarRotinaMensalEmTodasAsContas() {
        for contaCadastrada in listaDeContas {
            contaCadastrada.aplicarRotinaMensal()
        }
    }

    func gerarRelatorioGeral() -> String {
        var linhasDoRelatorio = [
            "Banco: \(nomeDoBanco)",
            "Gerente: \(gerenteResponsavel.gerarDescricaoDetalhada())",
            ""
        ]

        for clienteCadastrado in listaDeClientes {
            let patrimonioFormatado = FormatadorMonetario.formatarValorEmReais(
                valorMonetario: calcularPatrimonioTotalDoCliente(clienteProcurado: clienteCadastrado)
            )

            linhasDoRelatorio.append(clienteCadastrado.gerarDescricaoDetalhada())

            for contaDoCliente in listarContasDoCliente(clienteProcurado: clienteCadastrado) {
                linhasDoRelatorio.append("   " + contaDoCliente.gerarDescricaoDetalhada())
            }

            linhasDoRelatorio.append("   Patrimônio total: \(patrimonioFormatado)")
            linhasDoRelatorio.append("")
        }

        return linhasDoRelatorio.joined(separator: "\n")
    }
}
