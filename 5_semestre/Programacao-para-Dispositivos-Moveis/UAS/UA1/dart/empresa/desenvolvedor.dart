class Desenvolvedor extends Funcionario {
  NivelExperiencia nivel;
  List<String> linguagens;

  Desenvolvedor({
    required String nome,
    required String cpf,
    required double salarioBase,
    required Departamento departamento,
    required DateTime dataAdmissao,
    required this.nivel,
    required List<String> linguagens,
  })  : linguagens = List.from(linguagens),
        super(
          nome: nome,
          cpf: cpf,
          salarioBase: salarioBase,
          departamento: departamento,
          dataAdmissao: dataAdmissao,
        );

  void adicionarLinguagem(String linguagem) {
    if (!linguagens.contains(linguagem)) {
      linguagens.add(linguagem);
    }
  }

  @override
  double calcularBonus() {
    switch (nivel) {
      case NivelExperiencia.ESTAGIARIO:
        return salarioBase * 0.05;
      case NivelExperiencia.JUNIOR:
        return salarioBase * 0.08;
      case NivelExperiencia.PLENO:
        return salarioBase * 0.12;
      case NivelExperiencia.SENIOR:
        return salarioBase * 0.18;
      case NivelExperiencia.ESPECIALISTA:
        return salarioBase * 0.25;
    }
  }

  @override
  double calcularSalarioTotal() {
    return salarioBase + calcularBonus();
  }

  @override
  String toString() {
    return "[DEV] ${super.toString()} | Nível: $nivel | Linguagens: ${linguagens.join(", ")}";
  }
}