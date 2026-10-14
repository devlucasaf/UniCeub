class FolhaPagamento {
  Map<String, double> pagamentosRealizados = {};

  void processarPagamento(Funcionario f) {
    if (!f.ativo) {
      throw FuncionarioInativoException("Funcionário ${f.nome} está inativo.");
    }
    double valor = f.calcularSalarioTotal();
    pagamentosRealizados[f.id] = valor;
    print("Pagamento de R\$ ${valor.toStringAsFixed(2)} para ${f.nome}");
  }

  double totalPago() {
    return pagamentosRealizados.values.fold(0.0, (sum, v) => sum + v);
  }

  void limparRegistros() {
    pagamentosRealizados.clear();
  }
}