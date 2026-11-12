enum TipoTransacao {
    case deposito
    case saque
    case rendimento
    case tarifa

    var transacaoAumentaSaldo: Bool {
        switch self {
        case .deposito, .rendimento:
            return true
        case .saque, .tarifa:
            return false
        }
    }

    var nomeParaExibicao: String {
        switch self {
        case .deposito: return "Depósito"
        case .saque: return "Saque"
        case .rendimento: return "Rendimento"
        case .tarifa: return "Tarifa"
        }
    }
}
