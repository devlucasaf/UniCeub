enum Especie {
    Cachorro = "Cachorro",
    Gato = "Gato",
    Passaro = "Pássaro",
    Outro = "Outro"
}

interface AnimalInterface {
    nome: string;
    idade: number; // em anos
    especie: Especie;
    emitirSom(): string;
}

class Animal implements AnimalInterface {
    nome: string;
    idade: number;
    especie: Especie;
    protected vacinado: boolean = false; 

    constructor(nome: string, idade: number, especie: Especie) {
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
    }

    // Método público
    emitirSom(): string {
        switch (this.especie) {
            case Especie.Cachorro: {
                return "Au au!";
            }

            case Especie.Gato: {
                return "Miau!";
            }

            case Especie.Passaro: {
                return "Piu piu!";
            }

            default: {
                return "Som genérico de animal";
            }
        }
    }

    vacinar(): void {
        this.vacinado = true;
        console.log(`${this.nome} foi vacinado(a).`);
    }

    isVacinado(): boolean {
        return this.vacinado;
    }

    exibirInfo(): void {
        console.log(`
        --- Animal ---
        Nome: ${this.nome}
        Idade: ${this.idade} anos
        Espécie: ${this.especie}
        Vacinado: ${this.vacinado ? "Sim" : "Não"}
        Som: ${this.emitirSom()}
        `);
    }
}

class Cachorro extends Animal {
    private raca: string;

    constructor(nome: string, idade: number, raca: string) {
        super(nome, idade, Especie.Cachorro);
        this.raca = raca;
    }

    emitirSom(): string {
        return "Au au au! (latido feliz)";
    }

    buscarBolinha(): string {
        return `${this.nome} corre atrás da bolinha!`;
    }

    exibirInfo(): void {
        super.exibirInfo();
        console.log(`Raça: ${this.raca}`);
    }
}

class Gato extends Animal {
    private corPelagem: string;

    constructor(nome: string, idade: number, corPelagem: string) {
        super(nome, idade, Especie.Gato);
        this.corPelagem = corPelagem;
    }

    emitirSom(): string {
        return "Miau! (ronronando)";
    }

    arranharMoveis(): string {
        return `${this.nome} arranhou o sofá!`;
    }
}

interface ServicoAdicional {
    descricao: string;
    preco: number;
    realizar(): void;
}

class Consulta {
    private dados: [string, Animal, ServicoAdicional[]];

    constructor(data: string, animal: Animal, servicos: ServicoAdicional[] = []) {
        this.dados = [data, animal, servicos];
    }

    getData(): string {
        return this.dados[0];
    }

    getAnimal(): Animal {
        return this.dados[1];
    }

    getServicos(): ServicoAdicional[] {
        return this.dados[2];
    }

    adicionarServico(servico: ServicoAdicional): void {
        this.dados[2].push(servico);
    }

    calcularTotal(): number {
        let total = 0;

        for (let servico of this.dados[2]) {
            total += servico.preco;
        }

        return total;
    }

    detalhes(): void {
        console.log(`\n=== Consulta em ${this.dados[0]} ===`);
        this.dados[1].exibirInfo();
        if (this.dados[2].length === 0) {
            console.log("Nenhum serviço adicional.");
        } else {
            console.log("Serviços realizados:");
            for (let serv of this.dados[2]) {
                console.log(`- ${serv.descricao}: R$ ${serv.preco.toFixed(2)}`);
            }
            console.log(`Total: R$ ${this.calcularTotal().toFixed(2)}`);
        }
    }
}

// 8. CLASSE para gerenciar a clínica
class ClinicaVeterinaria {
    private nome: string;
    public animais: Animal[] = [];
    private consultas: Consulta[] = [];

    constructor(nome: string) {
        this.nome = nome;
    }

    adicionarAnimal(animal: Animal): void {
        this.animais.push(animal);
        console.log(`${animal.nome} adicionado à clínica ${this.nome}.`);
    }

    listarAnimais(): void {
        console.log(`\n--- Animais cadastrados em ${this.nome} ---`);

        if (this.animais.length === 0) {
            console.log("Nenhum animal cadastrado.");
            return;
        }

        for (let idx in this.animais) {
            console.log(`${Number(idx) + 1}. ${this.animais[idx].nome} - ${this.animais[idx].especie}`);
        }
    }

    agendarConsulta(data: string, animal: Animal, servicos?: ServicoAdicional[]): void {
        let consulta = new Consulta(data, animal, servicos || []);
        this.consultas.push(consulta);
        console.log(`Consulta agendada para ${animal.nome} em ${data}.`);
    }

    exibirConsultas(): void {
        console.log(`\n--- Consultas agendadas ---`);

        if (this.consultas.length === 0) {
            console.log("Nenhuma consulta registrada.");
            return;
        }

        for (let consulta of this.consultas) {
            consulta.detalhes();
        }
    }

    vacinarTodos(): void {
        console.log("\n--- Iniciando campanha de vacinação ---");
        let contador = 0;

        while (contador < this.animais.length) {
            let animal = this.animais[contador];

            if (!animal.isVacinado()) {
                animal.vacinar();
            }

            contador++;
        }

        console.log("Campanha finalizada.");
    }

    processarDadosExternos(dados: any): void {
        console.log("\n--- Processando dados externos (any) ---");
        if (typeof dados === "string") {
            console.log(`Dado recebido como string: ${dados}`);
        } 
        
        else if (typeof dados === "number") {
            console.log(`Dado recebido como número: ${dados}`);
        } 
        
        else if (Array.isArray(dados)) {
            console.log(`Array recebido com ${dados.length} elementos.`);
        } 
        
        else {
            console.log("Tipo de dado desconhecido.");
        }
    }
}

function calcularMediaIdade(animais: Animal[]): number {
    let soma = 0;

    for (let animal of animais) {
        soma += animal.idade;
    }

    return soma / animais.length;
}

function criarServico(descricao: string, preco: number, desconto?: number): ServicoAdicional {
    let precoFinal = desconto ? preco - desconto : preco;
    
    return {
        descricao: descricao,
        preco: precoFinal,
        
        realizar(): void {
            console.log(`Realizando ${this.descricao} - Valor: R$ ${this.preco.toFixed(2)}`);
        }
    };
}

console.log("=== INICIANDO SISTEMA DA CLÍNICA VETERINÁRIA ===\n");

const clinica = new ClinicaVeterinaria("Amigo Animal");

var varTeste = "Exemplo de var (escopo global)"; 
let idadeCachorro = 3; 
const taxaConsulta = 50.0; 

let rex = new Cachorro("Rex", idadeCachorro, "Labrador");
let mimi = new Gato("Mimi", 2, "Branca");
let pipoca = new Animal("Pipoca", 1, Especie.Passaro);
let desconhecido = new Animal("Zé", 5, Especie.Outro);

clinica.adicionarAnimal(rex);
clinica.adicionarAnimal(mimi);
clinica.adicionarAnimal(pipoca);
clinica.adicionarAnimal(desconhecido);

clinica.listarAnimais();

let banho = criarServico("Banho e tosa", 45.0);
let consultaRotina = criarServico("Consulta de rotina", taxaConsulta);
let vacina = criarServico("Vacina V10", 80.0, 10); 

clinica.agendarConsulta("2025-04-10", rex, [banho, consultaRotina]);
clinica.agendarConsulta("2025-04-11", mimi, [vacina]);

clinica.exibirConsultas();

console.log("\n--- Comportamentos específicos ---");
console.log(rex.buscarBolinha());
console.log(mimi.arranharMoveis());

clinica.vacinarTodos();

console.log("\n--- Status de vacinação após campanha ---");
console.log(`${rex.nome} vacinado? ${rex.isVacinado()}`);
console.log(`${mimi.nome} vacinado? ${mimi.isVacinado()}`);

let media = calcularMediaIdade(clinica.animais);
console.log(`\nMédia de idade dos animais: ${media.toFixed(1)} anos`);

clinica.processarDadosExternos("Mensagem de exemplo");
clinica.processarDadosExternos(12345);
clinica.processarDadosExternos([1, 2, 3]);

let tuplaExemplo: [string, number] = ["Rex", 3];
console.log(`\nTupla: ${tuplaExemplo[0]} tem ${tuplaExemplo[1]} anos.`);

let especiesListadas: Especie[] = [Especie.Cachorro, Especie.Gato, Especie.Passaro];
console.log("\nIteração com for...in (índices):");

for (let idx in especiesListadas) {
    console.log(`Índice ${idx} = ${especiesListadas[idx]}`);
}

console.log("\nIteração com for...of (valores):");

for (let especie of especiesListadas) {
    console.log(especie);
}

const clinicaConst = clinica;
clinicaConst.listarAnimais(); 

console.log("\n=== FIM DO SISTEMA ===");
