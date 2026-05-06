function acao(qntIteracoes: number): number[] {
    const impares: number[] = [];
    for (let i = qntIteracoes; i > 0; i--) {
        if (i % 2 !== 0) {
            impares.push(i);
        }
    }
    return impares;
}

console.log(acao(10)); // [9, 7, 5, 3, 1]
