package main

import (
    "fmt"
    "strings"
)

type Livro struct {
    Titulo     string
    Autor      string
    Emprestado bool
}

func (l *Livro) Emprestar() {
    l.Emprestado = true
}

func (l *Livro) Devolver() {
    l.Emprestado = false
}

func (l Livro) String() string {
    status := "Disponível"
    if l.Emprestado {
        status = "Emprestado"
    }
    return fmt.Sprintf("%s - %s (%s)", l.Titulo, l.Autor, status)
}

type Biblioteca struct {
    Livros []Livro
}

func (b *Biblioteca) AdicionarLivro(titulo, autor string) {
    b.Livros = append(b.Livros, Livro{Titulo: titulo, Autor: autor})
}

func (b *Biblioteca) ListarLivros() {
    if len(b.Livros) == 0 {
        fmt.Println("Nenhum livro cadastrado.")
        return
    }
    for i, livro := range b.Livros {
        fmt.Printf("%d. %s\n", i+1, livro.String())
    }
}

func (b *Biblioteca) BuscarPorTitulo(titulo string) {
    for _, livro := range b.Livros {
        if strings.EqualFold(livro.Titulo, titulo) {
            fmt.Println("Encontrado:", livro.String())
            return
        }
    }
    fmt.Println("Livro não encontrado.")
}

func (b *Biblioteca) EmprestarLivro(indice int) {
    if indice >= 0 && indice < len(b.Livros) {
        if !b.Livros[indice].Emprestado {
            b.Livros[indice].Emprestar()
            fmt.Println("Livro emprestado com sucesso.")
        } 
		else {
            fmt.Println("Livro já está emprestado.")
        }
    } 
	else {
        fmt.Println("Índice inválido.")
    }
}

func (b *Biblioteca) DevolverLivro(indice int) {
    if indice >= 0 && indice < len(b.Livros) {
        if b.Livros[indice].Emprestado {
            b.Livros[indice].Devolver()
            fmt.Println("Livro devolvido com sucesso.")
        } 
		else {
            fmt.Println("Livro não está emprestado.")
        }
    } 
	else {
        fmt.Println("Índice inválido.")
    }
}

func main() {
    biblioteca := Biblioteca{}
    var opcao int

    for {
        fmt.Println("\n--- Menu ---")
        fmt.Println("1. Adicionar livro")
        fmt.Println("2. Listar livros")
        fmt.Println("3. Buscar por título")
        fmt.Println("4. Emprestar livro")
        fmt.Println("5. Devolver livro")
        fmt.Println("0. Sair")
        fmt.Print("Escolha uma opção: ")
        fmt.Scanln(&opcao)

        switch opcao {
        case 1:
            var titulo, autor string
            fmt.Print("Título: ")
            fmt.Scanln(&titulo)
            fmt.Print("Autor: ")
            fmt.Scanln(&autor)
            biblioteca.AdicionarLivro(titulo, autor)
        case 2:
            biblioteca.ListarLivros()
        case 3:
            var busca string
            fmt.Print("Digite o título: ")
            fmt.Scanln(&busca)
            biblioteca.BuscarPorTitulo(busca)
        case 4:
            biblioteca.ListarLivros()
            var indice int
            fmt.Print("Digite o número do livro para emprestar: ")
            fmt.Scanln(&indice)
            biblioteca.EmprestarLivro(indice - 1)
        case 5:
            biblioteca.ListarLivros()
            var indice int
            fmt.Print("Digite o número do livro para devolver: ")
            fmt.Scanln(&indice)
            biblioteca.DevolverLivro(indice - 1)
        case 0:
            fmt.Println("Encerrando...")
            return
        default:
            fmt.Println("Opção inválida.")
        }
    }
}
