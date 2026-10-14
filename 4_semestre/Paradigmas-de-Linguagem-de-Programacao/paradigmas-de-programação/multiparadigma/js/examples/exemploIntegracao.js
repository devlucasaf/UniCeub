import Cachorro from "../models/Cachorro.js";
import Gato from "../models/Gato.js";

const apresentarAnimal = animal => {
    console.log(
        `Este é ${animal.nome}, ele diz: ${animal.falar()}`
    );
};

const executarExemploIntegracao = () => {
    console.log("Integração dos paradigmas:");

    const animais = [
        new Cachorro("Rex", 5),
        new Gato("Mimi", 3),
        new Cachorro("Bob", 2)
    ];

    // Funcional: map
    const falas = animais.map(
        animal => animal.falar()
    );

    console.log(
        "Falas dos animais:",
        falas.join(", ")
    );

    // Imperativo: loop
    for (const animal of animais) {
        console.log(
            `${animal.nome} tem ${animal.idade} anos e fala: ${animal.falar()}`
        );
    }

    // Polimorfismo
    animais.forEach(apresentarAnimal);

    // Misturando estilos
    const idades = animais.map(
        animal => animal.idade
    );

    const somaIdades = idades.reduce(
        (acumulador, idade) => acumulador + idade,
        0
    );

    const mediaIdade = somaIdades / idades.length;

    console.log(
        `A média de idade dos animais é ${mediaIdade.toFixed(2)} anos.`
    );
};

export default executarExemploIntegracao;
