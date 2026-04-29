class Estagiario extends Funcionario {
  int cargaHoraria;
  String supervisor;

  Estagiario({
    required String nome,
    required String cpf,
    required double salarioBase,
    required Departamento departamento,
    required DateTime dataAdmissao,
    required this.cargaHoraria,
    required this.supervisor,
  }) : super(
          nome: nome,
          cpf: cpf,
          salarioBase: salarioBase,
          departamento: departamento,
          dataAdmissao: dataAdmissao,
        );

  @override
  double calcularBonus() => salarioBase * 0.02;

  @override
  double calcularSalarioTotal() => salarioBase + calcularBonus();

  @override
  String toString() {
    return "[EST] ${super.toString()} | Carga: ${cargaHoraria}h | Supervisor: $supervisor";
  }
}