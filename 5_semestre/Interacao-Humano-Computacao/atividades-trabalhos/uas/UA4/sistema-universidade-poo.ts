class Pessoa {
    protected id: number
    protected nome: string
    protected email: string

    constructor(
        id: number, 
        nome: string, 
        email: string
    ) {
        this.id = id
        this.nome = nome
        this.email = email
    }

    getNome(): string {
        return this.nome
    }

    setNome(nome: string): void {
        this.nome = nome
    }

    getEmail(): string {
        return this.email
    }

    setEmail(email: string): void {
        this.email = email
    }

    apresentar(): string {
        return `Pessoa: ${this.nome} - Email: ${this.email}`
    }
}

class Aluno extends Pessoa {
    private matricula: number
    private curso: string
    private disciplinas: Disciplina[] = []

    constructor(
        id: number, 
        nome: string, 
        email: string, 
        matricula: number, 
        curso: string
    ) {
        super(
            id, 
            nome, 
            email
        )
        this.matricula = matricula
        this.curso = curso
    }

    getMatricula(): number {
        return this.matricula
    }

    getCurso(): string {
        return this.curso
    }

    adicionarDisciplina(disciplina: Disciplina): void {
        this.disciplinas.push(disciplina)
    }

    listarDisciplinas(): void {
        console.log(`Disciplinas do aluno ${this.nome}`)
        this.disciplinas.forEach(d => {
            console.log(d.getNome())
        })
    }

    apresentar(): string {
        return `Aluno: ${this.nome} - Curso: ${this.curso}`
    }
}

class Professor extends Pessoa {
    private departamento: string
    private disciplinas: Disciplina[] = []

    constructor(
        id: number, 
        nome: string,
        email: string, 
        departamento: string
    ) {
        super(
            id, 
            nome, 
            email
        )
        this.departamento = departamento
    }

    adicionarDisciplina(disciplina: Disciplina): void {
        this.disciplinas.push(disciplina)
    }

    listarDisciplinas(): void {
        console.log(`Professor ${this.nome} ministra:`)
        this.disciplinas.forEach(d => {
            console.log(d.getNome())
        })
    }

    apresentar(): string {
        return `Professor: ${this.nome} - Departamento: ${this.departamento}`
    }
}

class Disciplina {
    private id: number
    private nome: string
    private cargaHoraria: number
    private alunos: Aluno[] = []

    constructor(
        id: number, 
        nome: string, 
        cargaHoraria: number
    ) {
        this.id = id
        this.nome = nome
        this.cargaHoraria = cargaHoraria
    }

    getNome(): string {
        return this.nome
    }

    matricularAluno(aluno: Aluno): void {
        this.alunos.push(aluno)
    }

    listarAlunos(): void {
        console.log(`Alunos matriculados em ${this.nome}`)
        this.alunos.forEach(a => {
            console.log(a.getNome())
        })
    }
}

class Universidade {
    private nome: string
    private alunos: Aluno[] = []
    private professores: Professor[] = []
    private disciplinas: Disciplina[] = []

    constructor(nome: string) {
        this.nome = nome
    }

    adicionarAluno(aluno: Aluno): void {
        this.alunos.push(aluno)
    }

    adicionarProfessor(professor: Professor): void {
        this.professores.push(professor)
    }

    adicionarDisciplina(disciplina: Disciplina): void {
        this.disciplinas.push(disciplina)
    }

    listarAlunos(): void {
        console.log("Lista de alunos:")
        this.alunos.forEach(a => {
            console.log(a.apresentar())
        })
    }

    listarProfessores(): void {
        console.log("Lista de professores:")
        this.professores.forEach(p => {
            console.log(p.apresentar())
        })
    }

    listarDisciplinas(): void {
        console.log("Lista de disciplinas:")
        this.disciplinas.forEach(d => {
            console.log(d.getNome())
        })
    }
}

const universidade = new Universidade("Universidade Tech")

const aluno1 = new Aluno(1, "Lucas", "lucas@email.com", 1001, "Engenharia de Software")
const aluno2 = new Aluno(2, "Ana", "ana@email.com", 1002, "Ciência da Computação")

const professor1 = new Professor(1, "Carlos", "carlos@email.com", "Computação")
const professor2 = new Professor(2, "Marina", "marina@email.com", "Tecnologia")

const disciplina1 = new Disciplina(1, "Programação Orientada a Objetos", 80)
const disciplina2 = new Disciplina(2, "Estruturas de Dados", 60)

universidade.adicionarAluno(aluno1)
universidade.adicionarAluno(aluno2)

universidade.adicionarProfessor(professor1)
universidade.adicionarProfessor(professor2)

universidade.adicionarDisciplina(disciplina1)
universidade.adicionarDisciplina(disciplina2)

disciplina1.matricularAluno(aluno1)
disciplina1.matricularAluno(aluno2)

disciplina2.matricularAluno(aluno2)

aluno1.adicionarDisciplina(disciplina1)
aluno2.adicionarDisciplina(disciplina1)
aluno2.adicionarDisciplina(disciplina2)

professor1.adicionarDisciplina(disciplina1)
professor2.adicionarDisciplina(disciplina2)

universidade.listarAlunos()
universidade.listarProfessores()
universidade.listarDisciplinas()

disciplina1.listarAlunos()
disciplina2.listarAlunos()

aluno1.listarDisciplinas()
aluno2.listarDisciplinas()

professor1.listarDisciplinas()
professor2.listarDisciplinas()
