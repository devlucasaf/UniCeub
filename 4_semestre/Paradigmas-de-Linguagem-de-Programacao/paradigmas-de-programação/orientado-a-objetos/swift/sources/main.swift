import Foundation

let anoAtualDoSistema = 2026

func exibirTituloDaSecao(tituloDaSecao: String) {
    let linhaDeSeparacao = String(repeating: "=", count: 70)
    print("\n\(linhaDeSeparacao)\n \(tituloDaSecao)\n\(linhaDeSeparacao)")
}

func executarOperacaoComTratamentoDeErro(descricaoDaOperacao: String, operacaoParaExecutar: () throws -> Void) {
    do {
        try operacaoParaExecutar()
        print("[SUCESSO] \(descricaoDaOperacao)")
    } catch let erroBancario as ErroOperacaoBancaria {
        print("[FALHA]   \(descricaoDaOperacao) -> \(erroBancario.description)")
    } catch {
        print("[FALHA]   \(descricaoDaOperacao) -> Erro inesperado: \(error)")
    }
}

exibirTituloDaSecao(tituloDaSecao: "1. CLASSES E OBJETOS")
print("A classe é o molde; o objeto é a instância criada a partir dele.\n")

let gerenteDaAgencia = Funcionario(
    nomeCompleto: "Mariana Souza Lima",
    numeroDoCpf: "12345678901",
    anoDeNascimento: 1985,
    cargoOcupado: "Gerente de Agência",
    anoDeAdmissao: 2012
)

let bancoOrientadoAObjetos = Banco(nomeDoBanco: "Banco Orientado a Objetos", gerenteResponsavel: gerenteDaAgencia)

let clienteAnaBeatriz = bancoOrientadoAObjetos.cadastrarCliente(nomeCompleto: "Ana Beatriz Carvalho", numeroDoCpf: "98765432100", anoDeNascimento: 1998)
let clienteBrunoHenrique = bancoOrientadoAObjetos.cadastrarCliente(nomeCompleto: "Bruno Henrique Alves", numeroDoCpf: "45678912355", anoDeNascimento: 2001)

let contaCorrenteDaAna = bancoOrientadoAObjetos.abrirContaCorrente(
    titularDaConta: clienteAnaBeatriz,
    saldoInicial: 1000,
    valorDoLimiteChequeEspecial: 500,
    valorDaTarifaMensal: 25
)

let contaPoupancaDaAna = bancoOrientadoAObjetos.abrirContaPoupanca(
    titularDaConta: clienteAnaBeatriz,
    saldoInicial: 3000,
    taxaDeRendimentoMensal: 0.005
)

let contaCorrenteDoBruno = bancoOrientadoAObjetos.abrirContaCorrente(
    titularDaConta: clienteBrunoHenrique,
    saldoInicial: 200,
    valorDoLimiteChequeEspecial: 300,
    valorDaTarifaMensal: 15
)

print("Objetos criados:")
print(" - \(contaCorrenteDaAna.gerarDescricaoDetalhada())")
print(" - \(contaPoupancaDaAna.gerarDescricaoDetalhada())")
print(" - \(contaCorrenteDoBruno.gerarDescricaoDetalhada())")

exibirTituloDaSecao(tituloDaSecao: "2. ENCAPSULAMENTO")
print("O saldo só muda por métodos que validam as regras de negócio.")
print("A linha abaixo NÃO compila, porque o setter de 'saldoAtual' é privado:")
print("   contaCorrenteDaAna.saldoAtual = 1_000_000\n")

executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Depositar R$ 250,00 na conta da Ana") {
    try contaCorrenteDaAna.depositar(valorDoDeposito: 250)
}

executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Depositar -R$ 50,00 na conta da Ana") {
    try contaCorrenteDaAna.depositar(valorDoDeposito: -50)
}

print("\nCPF da Ana visto de fora da classe: \(clienteAnaBeatriz.numeroDoCpfMascarado)")
print("Saldo atual (apenas leitura): \(FormatadorMonetario.formatarValorEmReais(valorMonetario: contaCorrenteDaAna.saldoAtual))")

exibirTituloDaSecao(tituloDaSecao: "3. HERANÇA")
print("'Cliente' e 'Funcionario' herdam atributos e métodos de 'Pessoa'.\n")

print("Idade da Ana (método herdado de Pessoa): \(clienteAnaBeatriz.calcularIdadeAproximada(anoAtual: anoAtualDoSistema)) anos")
print("Idade da Mariana (método herdado de Pessoa): \(gerenteDaAgencia.calcularIdadeAproximada(anoAtual: anoAtualDoSistema)) anos")
print("Tempo de empresa da Mariana (método exclusivo de Funcionario): \(gerenteDaAgencia.calcularTempoDeEmpresa(anoAtual: anoAtualDoSistema)) anos")

exibirTituloDaSecao(tituloDaSecao: "4. POLIMORFISMO")
print("O mesmo método produz comportamentos diferentes conforme o tipo real do objeto.\n")

let listaDePessoasDoSistema: [Pessoa] = [gerenteDaAgencia, clienteAnaBeatriz, clienteBrunoHenrique]

for pessoaDoSistema in listaDePessoasDoSistema {
    print(" - \(pessoaDoSistema.gerarDescricaoDetalhada())")
}

print("\nSaque na Conta Corrente (usa o cheque especial):")
executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Bruno saca R$ 450,00 com saldo de R$ 200,00") {
    try contaCorrenteDoBruno.sacar(valorDoSaque: 450)
}

print("\nSaque na Conta Poupança (sem cheque especial e com limite de 3 saques):")
executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Ana saca R$ 4.000,00 da poupança") {
    try contaPoupancaDaAna.sacar(valorDoSaque: 4000)
}

for numeroDoSaque in 1...4 {
    executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Ana faz o saque nº \(numeroDoSaque) de R$ 10,00 da poupança") {
        try contaPoupancaDaAna.sacar(valorDoSaque: 10)
    }
}

exibirTituloDaSecao(tituloDaSecao: "5. ABSTRAÇÃO")
print("A função abaixo só conhece o protocolo, não o tipo concreto da conta.\n")

func exibirSaldoDeQualquerOperacaoFinanceira(nomeDaOperacao: String, operacaoFinanceira: ProtocoloOperacoesFinanceiras) {
    let saldoFormatado = FormatadorMonetario.formatarValorEmReais(valorMonetario: operacaoFinanceira.consultarSaldo())
    print(" - \(nomeDaOperacao): \(saldoFormatado)")
}

exibirSaldoDeQualquerOperacaoFinanceira(nomeDaOperacao: "Conta corrente da Ana", operacaoFinanceira: contaCorrenteDaAna)
exibirSaldoDeQualquerOperacaoFinanceira(nomeDaOperacao: "Conta poupança da Ana", operacaoFinanceira: contaPoupancaDaAna)
exibirSaldoDeQualquerOperacaoFinanceira(nomeDaOperacao: "Conta corrente do Bruno", operacaoFinanceira: contaCorrenteDoBruno)

exibirTituloDaSecao(tituloDaSecao: "6. COMPOSIÇÃO")
print("O Banco é formado por clientes, contas e um gerente, e coordena as operações entre eles.\n")

executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Transferir R$ 300,00 da Ana (\(contaCorrenteDaAna.numeroDaConta)) para o Bruno (\(contaCorrenteDoBruno.numeroDaConta))") {
    try bancoOrientadoAObjetos.transferirEntreContas(
        numeroDaContaDeOrigem: contaCorrenteDaAna.numeroDaConta,
        numeroDaContaDeDestino: contaCorrenteDoBruno.numeroDaConta,
        valorDaTransferencia: 300
    )
}

executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Transferir R$ 100,00 para a conta 9999") {
    try bancoOrientadoAObjetos.transferirEntreContas(
        numeroDaContaDeOrigem: contaCorrenteDaAna.numeroDaConta,
        numeroDaContaDeDestino: 9999,
        valorDaTransferencia: 100
    )
}

executarOperacaoComTratamentoDeErro(descricaoDaOperacao: "Transferir R$ 100,00 da Ana para ela mesma") {
    try contaCorrenteDaAna.transferir(valorDaTransferencia: 100, contaDeDestino: contaCorrenteDaAna)
}

exibirTituloDaSecao(tituloDaSecao: "7. ROTINA MENSAL (POLIMORFISMO EM LOTE)")
print("Uma única chamada: a corrente cobra tarifa e a poupança credita rendimento.\n")

bancoOrientadoAObjetos.executarRotinaMensalEmTodasAsContas()

for contaCadastrada in bancoOrientadoAObjetos.obterTodasAsContas() {
    print(" - \(contaCadastrada.gerarDescricaoDetalhada())")
}

exibirTituloDaSecao(tituloDaSecao: "8. EXTRATOS")

for contaCadastrada in bancoOrientadoAObjetos.obterTodasAsContas() {
    print(contaCadastrada.gerarExtratoCompleto())
    print("")
}

exibirTituloDaSecao(tituloDaSecao: "9. RELATÓRIO GERAL DO BANCO")
print(bancoOrientadoAObjetos.gerarRelatorioGeral())
