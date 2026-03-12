export class Produto {
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
