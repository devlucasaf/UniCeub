class Empresa {
  String nome;
  String cnpj;
  List<Funcionario> funcionarios;
  FolhaPagamento folha;

  Empresa(this.nome, this.cnpj)
      : funcionarios = [],
        folha = FolhaPagamento();

  void contratar(Funcionario f) {
    if (f != null && !funcionarios.contains(f)) {
      funcionarios.add(f);
    }
  }

  void demitir(String id) {
    Funcionario? func = funcionarios.cast<Funcionario?>().firstWhere(
        (f) => f!.id == id,
        orElse: () => null);
    if (func != null) {
      func.desativar();
      print("Funcionário ${func.nome} desligado.");
    }
  }

  List<Funcionario> getFuncionariosAtivos() {
    return funcionarios.where((f) => f.ativo).toList();
  }

  List<Funcionario> getFuncionariosPorDepartamento(Departamento d) {
    return funcionarios.where((f) => f.departamento == d && f.ativo).toList();
  }

  List<Desenvolvedor> getDesenvolvedoresPorLinguagem(String linguagem) {
    return funcionarios
        .where((f) => f is Desenvolvedor && f.ativo)
        .map((f) => f as Desenvolvedor)
        .where((dev) => dev.linguagens.contains(linguagem))
        .toList();
  }

  Funcionario? buscarFuncionarioPorCpf(String cpf) {
    try {
      return funcionarios.firstWhere((f) => f.cpf == cpf);
    } catch (_) {
      return null;
    }
  }

  void aplicarAumentoGlobal(double percentual) {
    for (var f in funcionarios) {
      if (f.ativo) {
        f.aumentarSalario(percentual);
      }
    }
  }

  double calcularTotalFolha() {
    return funcionarios
        .where((f) => f.ativo)
        .fold(0.0, (sum, f) => sum + f.calcularSalarioTotal());
  }

  void listarFuncionariosOrdenadosPorSalario() {
    var ativos = getFuncionariosAtivos();
    ativos.sort((a, b) => b.calcularSalarioTotal().compareTo(a.calcularSalarioTotal()));
    for (var f in ativos) {
      print(f);
    }
  }

  void processarPagamentoTodos() {
    print("=== PROCESSANDO FOLHA ===");
    for (var f in getFuncionariosAtivos()) {
      try {
        folha.processarPagamento(f);
      } on FuncionarioInativoException catch (e) {
        print(e);
      }
    }
    print("Total pago: R\$ ${folha.totalPago().toStringAsFixed(2)}");
  }

  void promoverDesenvolvedor(String id, NivelExperiencia novoNivel) {
    Funcionario? func = funcionarios.firstWhere(
        (f) => f.id == id && f is Desenvolvedor && f.ativo,
        orElse: () => null);
    if (func != null) {
      Desenvolvedor dev = func as Desenvolvedor;
      dev.aumentarSalario(15);
      print(
          "Desenvolvedor ${dev.nome} promovido de ${dev.nivel} para $novoNivel");
    }
  }
}