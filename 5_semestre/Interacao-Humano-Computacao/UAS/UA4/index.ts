import { Produto } from "./Produto";
import { Venda } from "./Venda";

const produto1 = new Produto(
    "Cadeira Gamer Ergonômica", 
    "Cadeira com ajuste de altura e inclinação", 
    1200.50, 
    "TechSeat", 
    true
);

const produto2 = new Produto(
    "Mesa de Escritório", 
    "Mesa em L com acabamento em madeira", 
    850.00, 
    "OfficeDesign", 
    true
);

const minhaVenda = new Venda([produto1, produto2]);

const totalVenda = minhaVenda.calcularTotal();

console.log(`Produto 1: ${produto1.nome} - R$ ${produto1.valorComercial}`);
console.log(`Produto 2: ${produto2.nome} - R$ ${produto2.valorComercial}`);
console.log(`---`);
console.log(`O valor total da venda é: R$ ${totalVenda.toFixed(2)}`);
