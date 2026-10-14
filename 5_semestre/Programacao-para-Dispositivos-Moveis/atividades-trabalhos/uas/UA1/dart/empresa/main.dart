void main() {
  Empresa empresa = Empresa("Tech Solutions Ltda", "12.345.678/0001-99");

  List<String> linguagensAna = ["Java", "Python"];
  Desenvolvedor ana = Desenvolvedor(
    nome: "Ana Silva",
    cpf: "111.222.333-44",
    salarioBase: 7500.0,
    departamento: Departamento.TECNOLOGIA,
    dataAdmissao: DateTime(2022, 3, 10),
    nivel: NivelExperiencia.PLENO,
    linguagens: linguagensAna,
  );

  List<String> linguagensCarlos = ["JavaScript", "TypeScript", "React"];
  Desenvolvedor carlos = Desenvolvedor(
    nome: "Carlos Souza",
    cpf: "222.333.444-55",
    salarioBase: 10500.0,
    departamento: Departamento.TECNOLOGIA,
    dataAdmissao: DateTime(2019, 8, 1),
    nivel: NivelExperiencia.SENIOR,
    linguagens: linguagensCarlos,
  );

  List<String> linguagensJulia = ["C#", ".NET"];
  Desenvolvedor julia = Desenvolvedor(
    nome: "Julia Mendes",
    cpf: "333.444.555-66",
    salarioBase: 5200.0,
    departamento: Departamento.TECNOLOGIA,
    dataAdmissao: DateTime(2023, 6, 15),
    nivel: NivelExperiencia.JUNIOR,
    linguagens: linguagensJulia,
  );

  Gerente roberto = Gerente(
    nome: "Roberto Alves",
    cpf: "444.555.666-77",
    salarioBase: 15000.0,
    departamento: Departamento.TECNOLOGIA,
    dataAdmissao: DateTime(2015, 2, 20),
    bonusGestao: 15.0,
  );

  Gerente fernanda = Gerente(
    nome: "Fernanda Lima",
    cpf: "555.666.777-88",
    salarioBase: 13500.0,
    departamento: Departamento.VENDAS,
    dataAdmissao: DateTime(2018, 11, 5),
    bonusGestao: 12.0,
  );

  Estagiario bruno = Estagiario(
    nome: "Bruno Rocha",
    cpf: "666.777.888-99",
    salarioBase: 1800.0,
    departamento: Departamento.MARKETING,
    dataAdmissao: DateTime(2024, 1, 10),
    cargaHoraria: 30,
    supervisor: "Fernanda Lima",
  );

  empresa.contratar(ana);
  empresa.contratar(carlos);
  empresa.contratar(julia);
  empresa.contratar(roberto);
  empresa.contratar(fernanda);
  empresa.contratar(bruno);

  roberto.adicionarSubordinado(ana);
  roberto.adicionarSubordinado(carlos);
  roberto.adicionarSubordinado(julia);
  fernanda.adicionarSubordinado(bruno);

  print("=== LISTA DE FUNCIONÁRIOS ===");
  empresa.getFuncionariosAtivos().forEach(print);

  print("\n=== TOTAL DA FOLHA MENSAL ===");
  print("R\$ ${empresa.calcularTotalFolha().toStringAsFixed(2)}");

  print("\n=== DESENVOLVEDORES QUE USAM JAVA ===");
  empresa.getDesenvolvedoresPorLinguagem("Java").forEach(print);

  print("\n=== FUNCIONÁRIOS DO DEPARTAMENTO TECNOLOGIA ===");
  empresa.getFuncionariosPorDepartamento(Departamento.TECNOLOGIA).forEach(print);

  print("\n=== APLICANDO AUMENTO DE 5% ===");
  empresa.aplicarAumentoGlobal(5.0);
  empresa.getFuncionariosAtivos().forEach(print);

  print("\n=== PROMOVENDO JULIA ===");
  empresa.promoverDesenvolvedor(julia.id, NivelExperiencia.PLENO);

  print("\n=== ORDENADOS POR SALÁRIO (DECRESCENTE) ===");
  empresa.listarFuncionariosOrdenadosPorSalario();

  print("\n=== PROCESSAMENTO DE PAGAMENTO ===");
  empresa.processarPagamentoTodos();

  print("\n=== BUSCA POR CPF ===");
  var encontrado = empresa.buscarFuncionarioPorCpf("111.222.333-44");
  if (encontrado != null) {
    print("Encontrado: $encontrado");
  } else {
    print("Não encontrado");
  }

  print("\n=== DEMITINDO UM FUNCIONÁRIO ===");
  empresa.demitir(julia.id);
  print("Após demissão, ativos:");
  empresa.getFuncionariosAtivos().forEach(print);

  print(
      "\n=== NOVA TENTATIVA DE PAGAR DEMITIDO (deve lançar exceção tratada internamente) ===");
  empresa.processarPagamentoTodos();
}
