import { Produto } from "./Produto";

export class Venda {
    private produtos: Produto[];

    constructor(produtos: Produto[]) {
        this.produtos = produtos;
    }

    public calcularTotal(): number {
        return this.produtos.reduce((total, produto) => total + produto.valorComercial, 0);
    }
}
