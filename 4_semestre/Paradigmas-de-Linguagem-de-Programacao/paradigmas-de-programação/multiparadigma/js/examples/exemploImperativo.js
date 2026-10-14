const executarExemploImperativo = numeros => {
    console.log("Exemplo imperativo:");

    for (const numero of numeros) {
        if (numero % 2 === 0) {
            console.log(`${numero} é par`);
        } else {
            console.log(`${numero} é ímpar`);
        }
    }
};

export default executarExemploImperativo;
