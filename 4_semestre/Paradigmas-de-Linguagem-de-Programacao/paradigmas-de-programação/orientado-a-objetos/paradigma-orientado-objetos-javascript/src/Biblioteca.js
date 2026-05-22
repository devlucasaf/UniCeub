export class Biblioteca {
    constructor(nome) {
        this._nome = nome;
        this._livros = [];
        this._usuarios = [];
        this._historicoEmprestimos = [];
    }

    adicionarLivro(livro) {
        const livroExistente = this._livros.find(l => l.isbn === livro.isbn);
        if (!livroExistente) {
            this._livros.push(livro);
            console.log(`Livro "${livro.titulo}" adicionado à biblioteca.`);
            return true;
        } else {
            console.log(`Livro com ISBN ${livro.isbn} já existe na biblioteca.`);
            return false;
        }
    }

    registrarUsuario(usuario) {
        const usuarioExistente = this._usuarios.find(u => u.id === usuario.id);
        if (!usuarioExistente) {
            this._usuarios.push(usuario);
            console.log(`Usuário ${usuario.nome} registrado com sucesso.`);
            return true;
        } else {
            console.log(`Usuário com ID ${usuario.id} já está registrado.`);
            return false;
        }
    }

    emprestarLivro(isbn, usuarioId) {
        const livro = this._livros.find(l => l.isbn === isbn);
        const usuario = this._usuarios.find(u => u.id === usuarioId);

        if (!livro) {
            console.log(`Livro com ISBN ${isbn} não encontrado.`);
            return false;
        }

        if (!usuario) {
            console.log(`Usuário com ID ${usuarioId} não encontrado.`);
            return false;
        }

        if (livro.disponivel && usuario.adicionarLivroEmprestado(livro)) {
            livro.emprestar(usuario);
            this._historicoEmprestimos.push({
                livro: livro.titulo,
                usuario: usuario.nome,
                data: new Date()
            });
            return true;
        } else {
            console.log(`Não foi possível emprestar o livro "${livro.titulo}".`);
            return false;
        }
    }

    devolverLivro(isbn, usuarioId) {
        const livro = this._livros.find(l => l.isbn === isbn);
        const usuario = this._usuarios.find(u => u.id === usuarioId);

        if (livro && usuario) {
            if (livro.devolver()) {
                usuario.removerLivroEmprestado(isbn);
                return true;
            }
        }
        return false;
    }

    buscarLivros(termo) {
        return this._livros.filter(livro => 
            livro.titulo.toLowerCase().includes(termo.toLowerCase()) || 
            livro.autor.toLowerCase().includes(termo.toLowerCase())
        );
    }

    gerarRelatorio() {
        console.log(`>>> RELATÓRIO DA BIBLIOTECA ${this._nome} <<<`);
        console.log(`Total de livros: ${this._livros.length}`);
        console.log(`Total de usuários: ${this._usuarios.length}`);
        
        const livrosDisponiveis = this._livros.filter(l => l.disponivel).length;
        console.log(`Livros disponíveis: ${livrosDisponiveis}`);
        console.log(`Livros emprestados: ${this._livros.length - livrosDisponiveis}`);
        
        console.log("\nÚltimos empréstimos:");
        const ultimosEmprestimos = this._historicoEmprestimos.slice(-5);
        ultimosEmprestimos.forEach(emp => {
            console.log(`  - ${emp.livro} para ${emp.usuario} em ${emp.data.toLocaleDateString()}`);
        });
    }
}