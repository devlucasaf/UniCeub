// a) Criação da classe Produto
class Produto {
    // Definindo os atributos com tipos específicos do TypeScript
    public nome: string;
    public descricao: string;
    public valorComercial: number;
    public fabricante: string;
    public emEstoque: boolean;

    constructor(
        nome: string, 
        descricao: string, 
        valor: number, 
        fabricante: string, 
        estoque: boolean
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorComercial = valor;
        this.fabricante = fabricante;
        this.emEstoque = estoque;
    }
}

// b) Criação da classe Venda
class Venda {
    private produtos: Produto[];

    constructor(produtos: Produto[]) {
        this.produtos = produtos;
    }

    // Método para somar todos os valores dos produtos
    public calcularTotal(): number {
        return this.produtos.reduce((total, produto) => total + produto.valorComercial, 0);
    }
}

// c) Instanciando produtos e realizando a soma
// Criando dois produtos (exemplo: uma cadeira e uma mesa)
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

// Criando o objeto de Venda com o array de produtos
const minhaVenda = new Venda([produto1, produto2]);

// Realizando a soma e exibindo o resultado
const totalVenda = minhaVenda.calcularTotal();

console.log(`Produto 1: ${produto1.nome} - R$ ${produto1.valorComercial}`);
console.log(`Produto 2: ${produto2.nome} - R$ ${produto2.valorComercial}`);
console.log(`---`);
console.log(`O valor total da venda é: R$ ${totalVenda.toFixed(2)}`);
