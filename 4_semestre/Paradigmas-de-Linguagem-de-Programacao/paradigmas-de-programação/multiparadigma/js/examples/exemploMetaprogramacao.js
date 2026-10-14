import Pessoa from "../models/Pessoa.js";

const executarExemploMetaprogramacao = () => {
    console.log("Exemplo de metaprogramação:");

    // Criando métodos dinamicamente
    ["andar", "correr", "pular"].forEach(acao => {
        Pessoa.prototype[acao] = function () {
            return `${this.nome} está executando a ação de ${acao}!`;
        };
    });

    const joao = new Pessoa("João");

    console.log(joao.andar());
    console.log(joao.correr());
    console.log(joao.pular());
};

export default executarExemploMetaprogramacao;
