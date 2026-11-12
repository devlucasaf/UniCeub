enum ErroOperacaoBancaria: Error, CustomStringConvertible {
    case valorInvalido(valorInformado: Double)
    case saldoInsuficiente(valorDisponivel: Double, valorSolicitado: Double)
    case limiteDeSaquesAtingido(quantidadeMaximaDeSaques: Int)
    case contaNaoEncontrada(numeroDaConta: Int)
    case transferenciaParaMesmaConta(numeroDaConta: Int)

    var description: String {
        switch self {
        case .valorInvalido(let valorInformado):
            return "Valor inválido: \(FormatadorMonetario.formatarValorEmReais(valorMonetario: valorInformado)). O valor precisa ser maior que zero."
        case .saldoInsuficiente(let valorDisponivel, let valorSolicitado):
            return "Saldo insuficiente. Disponível: \(FormatadorMonetario.formatarValorEmReais(valorMonetario: valorDisponivel)) | Solicitado: \(FormatadorMonetario.formatarValorEmReais(valorMonetario: valorSolicitado))."
        case .limiteDeSaquesAtingido(let quantidadeMaximaDeSaques):
            return "Limite de \(quantidadeMaximaDeSaques) saques por mês atingido."
        case .contaNaoEncontrada(let numeroDaConta):
            return "Conta número \(numeroDaConta) não encontrada."
        case .transferenciaParaMesmaConta(let numeroDaConta):
            return "Não é possível transferir da conta \(numeroDaConta) para ela mesma."
        }
    }
}
