class Pessoa: ProtocoloDescricaoDetalhada {

    let nomeCompleto: String
    let anoDeNascimento: Int
    private let numeroDoCpf: String

    init(nomeCompleto: String, numeroDoCpf: String, anoDeNascimento: Int) {
        self.nomeCompleto = nomeCompleto
        self.numeroDoCpf = numeroDoCpf
        self.anoDeNascimento = anoDeNascimento
    }

    var numeroDoCpfMascarado: String {
        let doisUltimosDigitosDoCpf = String(numeroDoCpf.suffix(2))
        return "***.***.***-\(doisUltimosDigitosDoCpf)"
    }

    func calcularIdadeAproximada(anoAtual: Int) -> Int {
        return anoAtual - anoDeNascimento
    }

    func gerarDescricaoDetalhada() -> String {
        return "\(nomeCompleto) | CPF: \(numeroDoCpfMascarado)"
    }
}
