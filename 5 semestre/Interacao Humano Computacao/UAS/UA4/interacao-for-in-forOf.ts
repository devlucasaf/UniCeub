// Com arrays - retorna os índices
const frutas = ["maçã", "banana", "laranja"];
for (let indice in frutas) {
    console.log(indice); // 0, 1, 2
}

// Com objetos - retorna as chaves
const pessoa = { nome: "João", idade: 30 };
for (let chave in pessoa) {
    console.log(chave); // "nome", "idade"
}

// Com arrays - retorna os valores
const esportes = ["futebol", "basquete", "natação"];
for (let valor of esportes) {
    console.log(valor); // "futebol", "basquete", "natação"
}

// Com strings - retorna cada caractere
for (let letra of "TypeScript") {
    console.log(letra); // T, y, p, e, S, c, r, i, p, t
}