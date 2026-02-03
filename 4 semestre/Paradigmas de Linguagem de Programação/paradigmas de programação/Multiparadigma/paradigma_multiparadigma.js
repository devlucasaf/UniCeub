// Paradigma Multiparadigma em JavaScript
// Demonstração de:
// - Orientação a Objetos (classes, herança, polimorfismo)
// - Funcional (map, filter, reduce, funções de ordem superior)
// - Imperativo (loops, condicionais)
// - Metaprogramação (adicionando métodos dinamicamente)

// -------------------------------
// Orientação a Objetos
// -------------------------------

class Animal {
    constructor(nome, idade) {
        this.nome = nome;
        this.idade = idade;
    }

    falar() {
        return "O animal faz um som.";
    }
}

class Cachorro extends Animal {
    falar() {
        return "Au au!";
    }
}

class Gato extends Animal {
    falar() {
        return "Miau!";
    }
}

// -------------------------------
// Funcional
// -------------------------------

const numeros = Array.from({ length: 10 }, (_, i) => i + 1);

// map
const quadrados = numeros.map(n => n ** 2);

// filter
const pares = numeros.filter(n => n % 2 === 0);

// reduce
const soma = numeros.reduce((acc, n) => acc + n, 0);

// função de ordem superior
const multiplicar = (a, b) => a * b;
const produto = multiplicar(5, 6);

// -------------------------------
// Imperativo
// -------------------------------

console.log("Exemplo imperativo:");
for (let n of numeros) {
    if (n % 2 === 0) {
        console.log(`${n} é par`);
    } else {
        console.log(`${n} é ímpar`);
    }
}

// -------------------------------
// Metaprogramação
// -------------------------------

class Pessoa {
    constructor(nome) {
        this.nome = nome;
    }
}

// Criando métodos dinamicamente
["andar", "correr", "pular"].forEach(acao => {
    Pessoa.prototype[acao] = function () {
        return `${this.nome} está ${acao}!`;
    };
});

const joao = new Pessoa("João");
console.log(joao.andar());
console.log(joao.correr());
console.log(joao.pular());

// -------------------------------
// Integração dos paradigmas
// -------------------------------

const animais = [
    new Cachorro("Rex", 5),
    new Gato("Mimi", 3),
    new Cachorro("Bob", 2)
];

// funcional: map
const falas = animais.map(a => a.falar());
console.log("Falas dos animais:", falas.join(", "));

// imperativo: loop
for (let animal of animais) {
    console.log(`${animal.nome} tem ${animal.idade} anos e fala: ${animal.falar()}`);
}

// -------------------------------
// Polimorfismo
// -------------------------------

function apresentar(animal) {
    console.log(`Este é ${animal.nome}, ele diz: ${animal.falar()}`);
}

animais.forEach(apresentar);

// -------------------------------
// Misturando estilos
// -------------------------------

const idades = animais.map(a => a.idade);
const mediaIdade = idades.reduce((acc, n) => acc + n, 0) / idades.length;
console.log(`A média de idade dos animais é ${mediaIdade.toFixed(2)} anos.`);

// -------------------------------
// Conclusão
// -------------------------------
console.log("Este código demonstra JavaScript como linguagem multiparadigma!");
