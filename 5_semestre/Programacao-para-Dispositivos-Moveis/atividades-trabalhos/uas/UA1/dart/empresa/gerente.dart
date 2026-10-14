class Gerente extends Funcionario {
  List<Funcionario> equipe;
  double bonusGestao; 

  Gerente({
    required String nome,
    required String cpf,
    required double salarioBase,
    required Departamento departamento,
    required DateTime dataAdmissao,
    required this.bonusGestao,
  })  : equipe = [],
        super(
          nome: nome,
          cpf: cpf,
          salarioBase: salarioBase,
          departamento: departamento,
          dataAdmissao: dataAdmissao,
        );

  void adicionarSubordinado(Funcionario f) {
    if (!equipe.contains(f)) {
      equipe.add(f);
    }
  }

  void removerSubordinado(Funcionario f) {
    equipe.remove(f);
  }

  @override
  double calcularBonus() {
    return salarioBase * (bonusGestao / 100) + (equipe.length * 100);
  }

  @override
  double calcularSalarioTotal() {
    return salarioBase + calcularBonus();
  }

  @override
  String toString() {
    return "[GER] ${super.toString()} | Bônus Gestão: $bonusGestao% | Equipe: ${equipe.length}";
  }
}