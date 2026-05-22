const prompt = require('prompt-sync')();

function calcularDesconto() {    
    let valorProduto        = parseFloat(prompt("Digite o valor do produto: "));
    let porcentagemDesconto = parseFloat(prompt("Digite a porcentagem de desconto (ex: 10 para 10%): "));
    
    if (isNaN(valorProduto) || isNaN(porcentagemDesconto)) {
        console.log("\nErro: Por favor, digite apenas números válidos!");
        return;
    }
    
    if (valorProduto <= 0) {
        console.log("\nErro: O valor do produto deve ser maior que zero!");
        return;
    }
    
    if (porcentagemDesconto < 0 || porcentagemDesconto > 100) {
        console.log("\nErro: A porcentagem de desconto deve estar entre 0 e 100!");
        return;
    }
    
    let valorDesconto   = valorProduto * (porcentagemDesconto / 100);
    let valorFinal      = valorProduto - valorDesconto;
    
    console.log("\nRESULTADO DO CÁLCULO");
    console.log(`Valor original: R$ ${valorProduto.toFixed(2)}`);
    console.log(`Desconto aplicado (${porcentagemDesconto}%): R$ ${valorDesconto.toFixed(2)}`);
    console.log(`Valor final com desconto: R$ ${valorFinal.toFixed(2)}`);
    console.log("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
}

calcularDesconto();