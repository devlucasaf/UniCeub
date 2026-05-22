export class Usuario {
    constructor(id, nome, email, tipo = 'Comum') {
        this._id = id;
        this._nome = nome;
        this._email = email;
        this._tipo = tipo; 
        this._livrosEmprestados = [];
        this._dataRegistro = new Date();
    }

    get id() {
        return this._id;
    }

    get nome() {
        return this._nome;
    }

    get livrosEmprestados() {
        return this._livrosEmprestados;
    }

    adicionarLivroEmprestado(livro) {
        if (this._livrosEmprestados.length < this.limiteEmprestimos()) {
            this._livrosEmprestados.push(livro);
            return true;
        } else {
            console.log(`${this._nome} atingiu o limite de empréstimos.`);
            return false;
        }
    }

    removerLivroEmprestado(isbn) {
        const index = this._livrosEmprestados.findIndex(livro => livro.isbn === isbn);
        if (index !== -1) {
            this._livrosEmprestados.splice(index, 1);
            return true;
        }
        return false;
    }

    limiteEmprestimos() {
        switch (this._tipo) {
            case 'Estudante': 
                return 5;
            case 'Professor': 
                return 10;
            default: 
                return 3;
        }
    }

    exibirInfo() {
        return `${this._nome} (${this._email}) - Tipo: ${this._tipo} - Livros emprestados: ${this._livrosEmprestados.length}`;
    }
}