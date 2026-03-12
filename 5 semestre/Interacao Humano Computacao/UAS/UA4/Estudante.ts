interface Pessoa {
    nome: string;
    idade: number;
    saudacao(): void;
}

class Estudante implements Pessoa {
    nome: string;
    idade: number;
    
    constructor(nome: string, idade: number) {
        this.nome = nome;
        this.idade = idade;
    }
    
    saudacao() {
        console.log(`Olá, meu nome é ${this.nome}`);
    }
}