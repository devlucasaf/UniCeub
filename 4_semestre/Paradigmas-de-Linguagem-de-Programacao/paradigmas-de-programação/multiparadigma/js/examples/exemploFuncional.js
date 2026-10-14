const executarExemploFuncional = () => {
    console.log("Exemplo funcional:");

    const numeros = Array.from(
        { length: 10 },
        (_, indice) => indice + 1
    );

    // Map
    const quadrados = numeros.map(
        numero => numero ** 2
    );

    // Filter
    const pares = numeros.filter(
        numero => numero % 2 === 0
    );

    // Reduce
    const soma = numeros.reduce(
        (acumulador, numero) => acumulador + numero,
        0
    );

    // Função
    const multiplicar = (numeroA, numeroB) => {
        return numeroA * numeroB;
    };

    const produto = multiplicar(5, 6);

    console.log("Números:", numeros);
    console.log("Quadrados:", quadrados);
    console.log("Números pares:", pares);
    console.log("Soma:", soma);
    console.log("Produto:", produto);

    return numeros;
};

export default executarExemploFuncional;
