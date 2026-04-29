abstract class Funcionario implements Bonus {
  final String          id;
  final String          nome;
  final String          cpf;
  final Departamento    departamento;
  final DateTime        dataAdmissao;
  double                salarioBase;
  bool                  ativo;

  Funcionario({
    required this.nome,
    required this.cpf,
    required this.salarioBase,
    required this.departamento,
    required this.dataAdmissao,
  })  : id = _gerarId(),
        ativo = true;

  static String _gerarId() {
    const chars = 'abcdefghijklmnopqrstuvwxyz0123456789';
    Random rnd = Random();
    return String.fromCharCodes(
      Iterable.generate(8, (_) => chars.codeUnitAt(rnd.nextInt(chars.length))),
    );
  }

  double calcularSalarioTotal();

  void desativar()  => ativo = false;
  void ativar()     => ativo = true;

  void aumentarSalario(double percentual) {
    if (percentual > 0) {
      salarioBase += salarioBase * (percentual / 100);
    }
  }

  @override
  String toString() {
    final fmt = "dd/MM/yyyy";
    final dataFormatada = "${dataAdmissao.day.toString().padLeft(2, '0')}/"
        "${dataAdmissao.month.toString().padLeft(2, '0')}/"
        "${dataAdmissao.year}";
    return "$id | $nome | $cpf | R\$ ${salarioBase.toStringAsFixed(2)} | $departamento | Adm: $dataFormatada | Ativo: $ativo";
  }
}