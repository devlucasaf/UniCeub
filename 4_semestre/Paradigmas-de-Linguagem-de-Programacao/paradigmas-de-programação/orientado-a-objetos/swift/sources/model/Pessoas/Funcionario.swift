final class Funcionario: Pessoa {

    let cargoOcupado: String
    let anoDeAdmissao: Int

    init(nomeCompleto: String, numeroDoCpf: String, anoDeNascimento: Int, cargoOcupado: String, anoDeAdmissao: Int) {
        self.cargoOcupado = cargoOcupado
        self.anoDeAdmissao = anoDeAdmissao
        super.init(nomeCompleto: nomeCompleto, numeroDoCpf: numeroDoCpf, anoDeNascimento: anoDeNascimento)
    }

    func calcularTempoDeEmpresa(anoAtual: Int) -> Int {
        return anoAtual - anoDeAdmissao
    }

    override func gerarDescricaoDetalhada() -> String {
        return "[Funcionário - \(cargoOcupado)] " + super.gerarDescricaoDetalhada()
    }
}
