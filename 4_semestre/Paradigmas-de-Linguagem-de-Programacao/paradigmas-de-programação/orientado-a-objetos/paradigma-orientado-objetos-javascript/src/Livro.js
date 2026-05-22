export class Livro {
    constructor(titulo, autor, isbn, anoPublicacao, genero) {
        this._titulo = titulo;
        this._autor = autor;
        this._isbn = isbn;
        this._anoPublicacao = anoPublicacao;
        this._genero = genero;
        this._disponivel = true;
        this._dataEmprestimo = null;
        this._usuarioEmprestimo = null;
    }

    get titulo() {
        return this._titulo;
    }

    get autor() {
        return this._autor;
    }

    get isbn() {
        return this._isbn;
    }

    get disponivel() {
        return this._disponivel;
    }

    emprestar(usuario) {
        if (this._disponivel) {
            this._disponivel = false;
            this._dataEmprestimo = new Date();
            this._usuarioEmprestimo = usuario;
            console.log(`Livro "${this._titulo}" emprestado para ${usuario.nome}.`);
            return true;
        } else {
            console.log(`Livro "${this._titulo}" não está disponível no momento.`);
            return false;
        }
    }

    devolver() {
        if (!this._disponivel) {
            this._disponivel = true;
            const tempoEmprestimo = Math.floor((new Date() - this._dataEmprestimo) / (1000 * 60 * 60 * 24));
            console.log(`Livro "${this._titulo}" devolvido após ${tempoEmprestimo} dias.`);
            this._dataEmprestimo = null;
            this._usuarioEmprestimo = null;
            return true;
        } else {
            console.log(`Livro "${this._titulo}" já está disponível.`);
            return false;
        }
    }

    exibirInfo() {
        return `"${this._titulo}" por ${this._autor} (${this._anoPublicacao}) - ${this._genero} - ISBN: ${this._isbn} - ${this._disponivel ? 'Disponível' : 'Emprestado'}`;
    }
}