/**
    Paradigmas de Linguagens de Programação
    Capítulo 4: Paradigmas de Orientação a Eventos - Lazarus
    Resumo do capítulo 4 em JavaScript
*/

class Controle {
    constructor(nome) {
        this.nome = nome;
        this.propriedades = {};
        this.eventos = {};
    }

    setPropriedade(chave, valor) {
        this.propriedades[chave] = valor;
    }

    on(evento, callback) {
    this.eventos[evento] = callback;
    }

    trigger(evento, ...args) {
        if (this.eventos[evento]) {
            this.eventos[evento](...args);
        } 
        else {
            console.log(`Nenhum manipulador para o evento '${evento}' em ${this.nome}`);
            }
    }
}

let btnSomar = new Controle("Botão Somar");
let btnLimpar = new Controle("Botão Limpar");
let edtNumero1 = new Controle("Campo Número 1");
let edtNumero2 = new Controle("Campo Número 2");

edtNumero1.setPropriedade("valor", 10);
edtNumero2.setPropriedade("valor", 20);

btnSomar.on("click", () => {
    let n1 = edtNumero1.propriedades.valor;
    let n2 = edtNumero2.propriedades.valor;
    let soma = n1 + n2;
    console.log("Resultado da soma:", soma);
});

btnLimpar.on("click", () => {
    edtNumero1.setPropriedade("valor", "");
    edtNumero2.setPropriedade("valor", "");
    console.log("Campos limpos. Foco em Campo Número 1.");
});

// ----------------------

btnSomar.trigger("click");   
btnLimpar.trigger("click");  
