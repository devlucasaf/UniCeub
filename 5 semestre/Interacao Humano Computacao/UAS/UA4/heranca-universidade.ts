class Pessoa {
    protected id: number
    protected nome: string
    protected idade: number
    protected email: string

    constructor(
        id: number, 
        nome: string, 
        idade: number,
        email: string
    ) {
        this.id = id
        this.nome = nome
        this.idade = idade
        this.email = email
    }

    getId(): number {
        return this.id
    }

    getNome(): string {
        return this.nome
    }

    getIdade(): number {
        return this.idade
    }

    getEmail(): string {
        return this.email
    }

    apresentar(): void {
        console.log(`Pessoa: ${this.nome} | Idade: ${this.idade} | Email: ${this.email}`)
    }
}

class Aluno extends Pessoa {
    protected matricula: string
    protected curso: string
    protected semestre: number

    constructor(
        id: number, 
        nome: string, 
        idade: number, 
        email: string, 
        matricula: string, 
        curso: string, 
        semestre: number
    ) {
        super(
            id, 
            nome, 
            idade, 
            email
        )
        this.matricula = matricula
        this.curso = curso
        this.semestre = semestre
    }

    estudar(): void {
        console.log(`${this.nome} está estudando para o curso de ${this.curso}`)
    }

    apresentar(): void {
        console.log(`Aluno: ${this.nome} | Curso: ${this.curso} | Semestre: ${this.semestre}`)
    }
}

class Professor extends Pessoa {
    protected disciplina: string
    protected salario: number

    constructor(
        id: number,
        nome: string, 
        idade: number, 
        email: string, 
        disciplina: string, 
        salario: number
    ) {
        super(
            id, 
            nome, 
            idade, 
            email
        )
        this.disciplina = disciplina
        this.salario = salario
    }

    lecionar(): void {
        console.log(`${this.nome} está lecionando ${this.disciplina}`)
    }

    apresentar(): void {
        console.log(`Professor: ${this.nome} | Disciplina: ${this.disciplina}`)
    }
}

class Funcionario extends Pessoa {
    protected cargo: string
    protected salario: number

    constructor(
        id: number, 
        nome: string, 
        idade: number, 
        email: string, 
        cargo: string, 
        salario: number
    ) {
        super(
            id, 
            nome, 
            idade, 
            email
        )
        this.cargo = cargo
        this.salario = salario
    }

    trabalhar(): void {
        console.log(`${this.nome} está trabalhando como ${this.cargo}`)
    }

    apresentar(): void {
        console.log(`Funcionário: ${this.nome} | Cargo: ${this.cargo}`)
    }
}

class Coordenador extends Professor {
    protected departamento: string

    constructor(
        id: number, 
        nome: string, 
        idade: number, 
        email: string, 
        disciplina: string, 
        salario: number, 
        departamento: string
    ) {
        super(
            id, 
            nome,
            idade,
            email, 
            disciplina, 
            salario
        )
        this.departamento = departamento
    }

    coordenar(): void {
        console.log(`${this.nome} está coordenando o departamento de ${this.departamento}`)
    }

    apresentar(): void {
        console.log(`Coordenador: ${this.nome} | Departamento: ${this.departamento}`)
    }
}

class Monitor extends Aluno {
    protected disciplinaMonitoria: string

    constructor(
        id: number, 
        nome: string, 
        idade: number, 
        email: string, 
        matricula: string, 
        curso: string, 
        semestre: number, 
        disciplinaMonitoria: string
    ) {
        super(
            id, 
            nome, 
            idade, 
            email, 
            matricula, 
            curso, 
            semestre
        )
        this.disciplinaMonitoria = disciplinaMonitoria
    }

    auxiliar(): void {
        console.log(`${this.nome} está auxiliando alunos na disciplina ${this.disciplinaMonitoria}`)
    }

    apresentar(): void {
        console.log(`Monitor: ${this.nome} | Disciplina: ${this.disciplinaMonitoria}`)
    }
}

class Universidade {
    private pessoas: Pessoa[] = []

    adicionarPessoa(p: Pessoa): void {
        this.pessoas.push(p)
    }

    listarPessoas(): void {
        this.pessoas.forEach(p => {
            p.apresentar()
        })
    }
}

const universidade = new Universidade()

const aluno1 = new Aluno(
    1,
    "Lucas",
    22,
    "lucas@email.com",
    "20231234",
    "Engenharia de Software",
    4
)

const professor1 = new Professor(
    2,
    "Carlos",
    45,
    "carlos@email.com",
    "Programação Orientada a Objetos",
    8500
)

const funcionario1 = new Funcionario(
    3,
    "Mariana",
    35,
    "mariana@email.com",
    "Secretária",
    3200
)

const coordenador1 = new Coordenador(
    4,
    "Ricardo",
    50,
    "ricardo@email.com",
    "Arquitetura de Software",
    12000,
    "Computação"
)

const monitor1 = new Monitor(
    5,
    "Ana",
    21,
    "ana@email.com",
    "20239876",
    "Ciência da Computação",
    3,
    "Estrutura de Dados"
)

universidade.adicionarPessoa(aluno1)
universidade.adicionarPessoa(professor1)
universidade.adicionarPessoa(funcionario1)
universidade.adicionarPessoa(coordenador1)
universidade.adicionarPessoa(monitor1)

universidade.listarPessoas()

aluno1.estudar()
professor1.lecionar()
funcionario1.trabalhar()
coordenador1.coordenar()
monitor1.auxiliar()
