final class Cliente: Pessoa {

    let codigoDoCliente: Int

    init(nomeCompleto: String, numeroDoCpf: String, anoDeNascimento: Int, codigoDoCliente: Int) {
        self.codigoDoCliente = codigoDoCliente

        super.init(nomeCompleto: nomeCompleto, numeroDoCpf: numeroDoCpf, anoDeNascimento: anoDeNascimento)
    }

    override func gerarDescricaoDetalhada() -> String {
        return "[Cliente nº \(codigoDoCliente)] " + super.gerarDescricaoDetalhada()
    }
}
