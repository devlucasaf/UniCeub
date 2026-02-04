require "json"
require "date"

class Curso
  attr_reader :codigo, :nome, :creditos, :pre_requisitos

  def initialize(codigo:, nome:, creditos:, pre_requisitos: [])
    @codigo = codigo
    @nome = nome
    @creditos = Integer(creditos)
    @pre_requisitos = pre_requisitos.map(&:to_s)
  end

  def to_h
    { codigo: @codigo, nome: @nome, creditos: @creditos, pre_requisitos: @pre_requisitos }
  end
end

class Aluno
  attr_reader :ra, :nome, :ingresso_em

  def initialize(ra:, nome:, ingresso_em: Date.today)
    @ra = ra.to_s
    @nome = nome.to_s
    @ingresso_em = ingresso_em
    @matriculas = []
  end

  def matriculas
    @matriculas.dup
  end

  def adicionar_matricula(matricula)
    @matriculas << matricula
  end

  def historico
    @matriculas.select { |m| m.status_finalizado? }
  end

  def cursando
    @matriculas.select { |m| m.status == :cursando }
  end

  def aprovado_em?(codigo_curso)
    historico.any? { |m| m.curso.codigo == codigo_curso && m.aprovado? }
  end

  def cursos_aprovados
    historico.select(&:aprovado?).map { |m| m.curso.codigo }.uniq
  end

  def cr(usar_pesos: true)
    concluidas = historico.select(&:avaliado?)
    return 0.0 if concluidas.empty?

    if usar_pesos
      soma_pesos = concluidas.reduce(0) { |acc, m| acc + m.curso.creditos }
      return 0.0 if soma_pesos == 0
      soma = concluidas.reduce(0.0) { |acc, m| acc + (m.nota_final * m.curso.creditos) }
      (soma / soma_pesos).round(2)
    else
      soma = concluidas.reduce(0.0) { |acc, m| acc + m.nota_final }
      (soma / concluidas.size).round(2)
    end
  end

  def creditos_concluidos
    historico.select(&:aprovado?).reduce(0) { |acc, m| acc + m.curso.creditos }
  end

  def to_h
    {
      ra: @ra,
      nome: @nome,
      ingresso_em: @ingresso_em.to_s,
      cr: cr,
      creditos_concluidos: creditos_concluidos
    }
  end
end

class Matricula
  STATUSES = [:cursando, :trancado, :cancelado, :reprovado, :aprovado].freeze

  attr_reader :aluno, :curso, :periodo
  attr_accessor :status

  def initialize(aluno:, curso:, periodo:)
    @aluno = aluno
    @curso = curso
    @periodo = periodo.to_s
    @status = :cursando
    @notas = []
    @frequencia = nil
    @data_criacao = Date.today
  end

  def registrar_avaliacao(valor)
    raise "Matrícula não está cursando" unless @status == :cursando
    v = Float(valor)
    raise "Nota inválida" if v < 0.0 || v > 10.0
    @notas << v
  end

  def definir_frequencia(percentual)
    p = Float(percentual)
    raise "Frequência inválida" if p < 0.0 || p > 100.0
    @frequencia = p
  end

  def notas
    @notas.dup
  end

  def frequencia
    @frequencia
  end

  def nota_final
    return 0.0 if @notas.empty?
    (@notas.reduce(0.0, :+) / @notas.size).round(2)
  end

  def avaliado?
    !@notas.empty?
  end

  def status_finalizado?
    [:aprovado, :reprovado, :cancelado].include?(@status)
  end

  def trancar!
    raise "Só pode trancar se estiver cursando" unless @status == :cursando
    @status = :trancado
  end

  def cancelar!
    raise "Só pode cancelar se estiver cursando ou trancado" unless [:cursando, :trancado].include?(@status)
    @status = :cancelado
  end

  def finalizar!(media_minima: 6.0, freq_minima: 75.0)
    raise "Só pode finalizar se estiver cursando" unless @status == :cursando
    raise "Frequência não informada" if @frequencia.nil?

    if @frequencia < freq_minima
      @status = :reprovado
      return @status
    end

    @status = nota_final >= media_minima ? :aprovado : :reprovado
  end

  def aprovado?
    @status == :aprovado
  end

  def reprovado?
    @status == :reprovado
  end

  def to_h
    {
      aluno_ra: @aluno.ra,
      curso: @curso.to_h,
      periodo: @periodo,
      status: @status,
      notas: @notas,
      frequencia: @frequencia,
      nota_final: nota_final,
      criado_em: @data_criacao.to_s
    }
  end
end

class Faculdade
  def initialize(nome:)
    @nome = nome.to_s
    @alunos = {}
    @cursos = {}
    @matriculas = []
  end

  def cadastrar_aluno(ra:, nome:, ingresso_em: Date.today)
    ra = ra.to_s
    raise "RA já cadastrado" if @alunos.key?(ra)
    aluno = Aluno.new(ra: ra, nome: nome, ingresso_em: ingresso_em)
    @alunos[ra] = aluno
    aluno
  end

  def cadastrar_curso(codigo:, nome:, creditos:, pre_requisitos: [])
    codigo = codigo.to_s
    raise "Curso já existe" if @cursos.key?(codigo)
    curso = Curso.new(codigo: codigo, nome: nome, creditos: creditos, pre_requisitos: pre_requisitos)
    @cursos[codigo] = curso
    curso
  end

  def aluno(ra)
    @alunos[ra.to_s]
  end

  def curso(codigo)
    @cursos[codigo.to_s]
  end

  def listar_alunos
    @alunos.values.sort_by(&:ra)
  end

  def listar_cursos
    @cursos.values.sort_by(&:codigo)
  end

  def matricular(ra:, codigo_curso:, periodo:)
    aluno = aluno(ra) or raise "Aluno não encontrado"
    curso = curso(codigo_curso) or raise "Curso não encontrado"

    if aluno.cursando.any? { |m| m.curso.codigo == curso.codigo && m.periodo == periodo.to_s }
      raise "Já existe matrícula ativa nesta disciplina e período"
    end

    faltantes = curso.pre_requisitos.reject { |pre| aluno.aprovado_em?(pre) }
    raise "Pré-requisitos não cumpridos: #{faltantes.join(', ')}" unless faltantes.empty?

    matricula = Matricula.new(aluno: aluno, curso: curso, periodo: periodo)
    @matriculas << matricula
    aluno.adicionar_matricula(matricula)
    matricula
  end

  def matriculas_do_aluno(ra)
    aluno = aluno(ra) or raise "Aluno não encontrado"
    aluno.matriculas
  end

  def matriculas_do_periodo(periodo)
    p = periodo.to_s
    @matriculas.select { |m| m.periodo == p }
  end

  def lancar_nota(ra:, codigo_curso:, periodo:, nota:)
    m = encontrar_matricula_ativa(ra, codigo_curso, periodo)
    m.registrar_avaliacao(nota)
    m
  end

  def lancar_frequencia(ra:, codigo_curso:, periodo:, frequencia:)
    m = encontrar_matricula_ativa(ra, codigo_curso, periodo)
    m.definir_frequencia(frequencia)
    m
  end

  def finalizar_matricula(ra:, codigo_curso:, periodo:, media_minima: 6.0, freq_minima: 75.0)
    m = encontrar_matricula_ativa(ra, codigo_curso, periodo)
    m.finalizar!(media_minima: media_minima, freq_minima: freq_minima)
    m
  end

  def trancar(ra:, codigo_curso:, periodo:)
    m = encontrar_matricula_por_status(ra, codigo_curso, periodo, [:cursando])
    m.trancar!
    m
  end

  def cancelar(ra:, codigo_curso:, periodo:)
    m = encontrar_matricula_por_status(ra, codigo_curso, periodo, [:cursando, :trancado])
    m.cancelar!
    m
  end

  def relatorio_periodo(periodo)
    mats = matriculas_do_periodo(periodo)
    {
      periodo: periodo.to_s,
      total_matriculas: mats.size,
      cursando: mats.count { |m| m.status == :cursando },
      trancado: mats.count { |m| m.status == :trancado },
      finalizados: mats.count(&:status_finalizado?),
      aprovados: mats.count(&:aprovado?),
      reprovados: mats.count(&:reprovado?)
    }
  end

  def exportar_json
    payload = {
      faculdade: @nome,
      alunos: listar_alunos.map(&:to_h),
      cursos: listar_cursos.map(&:to_h),
      matriculas: @matriculas.map(&:to_h)
    }
    JSON.pretty_generate(payload)
  end

  private

  def encontrar_matricula_ativa(ra, codigo_curso, periodo)
    encontrar_matricula_por_status(ra, codigo_curso, periodo, [:cursando])
  end

  def encontrar_matricula_por_status(ra, codigo_curso, periodo, statuses)
    aluno = aluno(ra) or raise "Aluno não encontrado"
    codigo = codigo_curso.to_s
    per = periodo.to_s
    m = aluno.matriculas.find { |x| x.curso.codigo == codigo && x.periodo == per && statuses.include?(x.status) }
    raise "Matrícula não encontrada com status #{statuses.join('/')}" unless m
    m
  end
end

faculdade = Faculdade.new(nome: "Faculdade Horizonte")

faculdade.cadastrar_curso(codigo: "CS101", nome: "Algoritmos", creditos: 4)
faculdade.cadastrar_curso(codigo: "MAT01", nome: "Cálculo I", creditos: 6)
faculdade.cadastrar_curso(codigo: "CS102", nome: "Estruturas de Dados", creditos: 4, pre_requisitos: ["CS101"])
faculdade.cadastrar_curso(codigo: "ENG10", nome: "Comunicação e Escrita", creditos: 2)

faculdade.cadastrar_aluno(ra: "2026001", nome: "Lucas Freitas", ingresso_em: Date.new(2026, 2, 1))
faculdade.cadastrar_aluno(ra: "2026002", nome: "Marina Souza", ingresso_em: Date.new(2026, 2, 1))

periodo_1 = "2026.1"
m1 = faculdade.matricular(ra: "2026001", codigo_curso: "CS101", periodo: periodo_1)
m2 = faculdade.matricular(ra: "2026001", codigo_curso: "MAT01", periodo: periodo_1)
m3 = faculdade.matricular(ra: "2026002", codigo_curso: "CS101", periodo: periodo_1)
m4 = faculdade.matricular(ra: "2026002", codigo_curso: "ENG10", periodo: periodo_1)

[
  ["2026001", "CS101", 7.5, 8.0, 90],
  ["2026001", "MAT01", 5.0, 6.0, 82],
  ["2026002", "CS101", 4.0, 5.5, 78],
  ["2026002", "ENG10", 9.0, 9.5, 96]
].each do |ra, cod, n1, n2, freq|
  faculdade.lancar_nota(ra: ra, codigo_curso: cod, periodo: periodo_1, nota: n1)
  faculdade.lancar_nota(ra: ra, codigo_curso: cod, periodo: periodo_1, nota: n2)
  faculdade.lancar_frequencia(ra: ra, codigo_curso: cod, periodo: periodo_1, frequencia: freq)
end

faculdade.finalizar_matricula(ra: "2026001", codigo_curso: "CS101", periodo: periodo_1)
faculdade.finalizar_matricula(ra: "2026001", codigo_curso: "MAT01", periodo: periodo_1)
faculdade.finalizar_matricula(ra: "2026002", codigo_curso: "CS101", periodo: periodo_1)
faculdade.finalizar_matricula(ra: "2026002", codigo_curso: "ENG10", periodo: periodo_1)

periodo_2 = "2026.2"
faculdade.matricular(ra: "2026001", codigo_curso: "CS102", periodo: periodo_2)
faculdade.matricular(ra: "2026001", codigo_curso: "ENG10", periodo: periodo_2)

faculdade.lancar_nota(ra: "2026001", codigo_curso: "CS102", periodo: periodo_2, nota: 8.0)
faculdade.lancar_nota(ra: "2026001", codigo_curso: "CS102", periodo: periodo_2, nota: 7.0)
faculdade.lancar_frequencia(ra: "2026001", codigo_curso: "CS102", periodo: periodo_2, frequencia: 88)

faculdade.lancar_nota(ra: "2026001", codigo_curso: "ENG10", periodo: periodo_2, nota: 10.0)
faculdade.lancar_nota(ra: "2026001", codigo_curso: "ENG10", periodo: periodo_2, nota: 9.0)
faculdade.lancar_frequencia(ra: "2026001", codigo_curso: "ENG10", periodo: periodo_2, frequencia: 92)

faculdade.finalizar_matricula(ra: "2026001", codigo_curso: "CS102", periodo: periodo_2)
faculdade.finalizar_matricula(ra: "2026001", codigo_curso: "ENG10", periodo: periodo_2)

puts "Relatório #{periodo_1}: #{faculdade.relatorio_periodo(periodo_1)}"
puts "Relatório #{periodo_2}: #{faculdade.relatorio_periodo(periodo_2)}"

al1 = faculdade.aluno("2026001")
al2 = faculdade.aluno("2026002")

puts "#{al1.nome} | CR: #{al1.cr} | Créditos concluídos: #{al1.creditos_concluidos}"
puts "#{al2.nome} | CR: #{al2.cr} | Créditos concluídos: #{al2.creditos_concluidos}"

puts faculdade.exportar_json
