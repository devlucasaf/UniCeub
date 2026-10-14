package poojava;

import java.util.*;

class Universidade {
    private final String                nome;
    private final List<Departamento>    departamentos;
    private final Map<UUID, Aluno>      alunosPorId;
    private final Map<String, Aluno>    alunosPorCodigo;
    private final List<Matricula>       matriculas;
    private final Map<String, Curso>    indiceCursos;
    private final Map<UUID, Fatura>     faturasPorId;
    private final Map<String, Fatura>   faturaPorCodigoAluno;

    public Universidade(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da universidade inválido");
        }
        
        this.nome = nome.trim();
        this.departamentos = new ArrayList<>();
        this.alunosPorId = new HashMap<>();
        this.alunosPorCodigo = new HashMap<>();
        this.matriculas = new ArrayList<>();
        this.indiceCursos = new HashMap<>();
        this.faturasPorId = new HashMap<>();
        this.faturaPorCodigoAluno = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarDepartamento(Departamento d) {
        departamentos.add(Objects.requireNonNull(d));
        
        for (Curso c : d.getCursos()) {
            indiceCursos.put(c.getCodigo(), c);
        }
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public void registrarAluno(Aluno a) {
        Objects.requireNonNull(a);
        alunosPorId.put(a.getId(), a);
        alunosPorCodigo.put(a.getIdAluno(), a);
    }

    public Aluno buscarAlunoPorCodigo(String codigo) {
        return alunosPorCodigo.get(codigo);
    }

    public Matricula matricular(Aluno a, Curso c) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(c);

        Matricula m = new Matricula(a, c);

        a.adicionarMatricula(m);

        matriculas.add(m);

        return m;
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    public Fatura criarFatura(Aluno a, int parcelas, double total) {
        Objects.requireNonNull(a);

        Fatura f = new Fatura(a, parcelas, total);

        faturasPorId.put(f.getId(), f);
        faturaPorCodigoAluno.put(a.getIdAluno(), f);

        return f;
    }

    public Fatura getFaturaPara(Aluno a) {
        return faturaPorCodigoAluno.get(a.getIdAluno());
    }

    public void pagarFatura(UUID idFatura, double valor) {
        Fatura f = faturasPorId.get(idFatura);
        if (f == null) {
            throw new IllegalArgumentException("Fatura não encontrada");
        }

        f.pagar(valor);
    }
}