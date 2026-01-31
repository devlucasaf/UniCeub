/**
    Paradigmas de Linguagens de Programação
    Aplicação de POO em JavaScript
*/

class Livro {
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

class Usuario {
    constructor(id, nome, email, tipo = 'Comum') {
        this._id = id;
        this._nome = nome;
        this._email = email;
        this._tipo = tipo; 
        this._livrosEmprestados = [];
        this._dataRegistro = new Date();
    }

    // Método Get

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

class Biblioteca {
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

const bibliotecaMunicipal = new Biblioteca("Biblioteca Municipal Digital");

const livro1 = new Livro("Dom Casmurro", "Machado de Assis", "978-85-7232-144-9", 1899, "Romance");
const livro2 = new Livro("1984", "George Orwell", "978-85-359-0277-5", 1949, "Ficção Científica");
const livro3 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-85-325-1790-3", 1954, "Fantasia");
const livro4 = new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "978-85-01-07504-8", 1967, "Realismo Mágico");

bibliotecaMunicipal.adicionarLivro(livro1);
bibliotecaMunicipal.adicionarLivro(livro2);
bibliotecaMunicipal.adicionarLivro(livro3);
bibliotecaMunicipal.adicionarLivro(livro4);

const usuario1 = new Usuario(1, "João Silva", "joao@email.com", "Estudante");
const usuario2 = new Usuario(2, "Maria Santos", "maria@email.com", "Professor");
const usuario3 = new Usuario(3, "Pedro Oliveira", "pedro@email.com", "Comum");

bibliotecaMunicipal.registrarUsuario(usuario1);
bibliotecaMunicipal.registrarUsuario(usuario2);
bibliotecaMunicipal.registrarUsuario(usuario3);

console.log("\n>>> REALIZANDO EMPRÉSTIMOS <<<");
bibliotecaMunicipal.emprestarLivro("978-85-7232-144-9", 1); 
bibliotecaMunicipal.emprestarLivro("978-85-359-0277-5", 2); 
bibliotecaMunicipal.emprestarLivro("978-85-325-1790-3", 2); 

bibliotecaMunicipal.emprestarLivro("978-85-7232-144-9", 3); 

console.log("\n>>> REALIZANDO DEVOLUÇÕES <<<");
bibliotecaMunicipal.devolverLivro("978-85-7232-144-9", 1); 

console.log("\n>>> RESULTADOS DE BUSCA <<<");
const resultados = bibliotecaMunicipal.buscarLivros("Orwell");
console.log("Busca por 'Orwell':");
resultados.forEach(livro => console.log(`  - ${livro.exibirInfo()}`));

console.log("\n>>> INFORMAÇÕES DOS USUÁRIOS <<<");
console.log(usuario1.exibirInfo());
console.log(usuario2.exibirInfo());
console.log(usuario3.exibirInfo());

console.log("\n>>> RELATÓRIO DA BIBLIOTECA <<<");
bibliotecaMunicipal.gerarRelatorio();
