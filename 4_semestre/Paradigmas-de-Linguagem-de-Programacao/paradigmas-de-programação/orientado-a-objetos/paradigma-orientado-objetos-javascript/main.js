import { Livro }        from './src/Livro.js';
import { Usuario }      from './src/Usuario.js';
import { Biblioteca }   from './src/Biblioteca.js';

// --- INSTÂNCIA DA BIBLIOTECA ---
const bibliotecaMunicipal = new Biblioteca("Biblioteca Municipal Digital");

// --- CRIANDO LIVROS ---
const livro1 = new Livro("Dom Casmurro", "Machado de Assis", "978-85-7232-144-9", 1899, "Romance");
const livro2 = new Livro("1984", "George Orwell", "978-85-359-0277-5", 1949, "Ficção Científica");
const livro3 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-85-325-1790-3", 1954, "Fantasia");
const livro4 = new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "978-85-01-07504-8", 1967, "Realismo Mágico");

// --- ADICIONANDO LIVROS ---
bibliotecaMunicipal.adicionarLivro(livro1);
bibliotecaMunicipal.adicionarLivro(livro2);
bibliotecaMunicipal.adicionarLivro(livro3);
bibliotecaMunicipal.adicionarLivro(livro4);

// --- CRIANDO USUÁRIOS ---
const usuario1 = new Usuario(1, "João Silva", "joao@email.com", "Estudante");
const usuario2 = new Usuario(2, "Maria Santos", "maria@email.com", "Professor");
const usuario3 = new Usuario(3, "Pedro Oliveira", "pedro@email.com", "Comum");

// --- REGISTRANDO USUÁRIOS ---
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