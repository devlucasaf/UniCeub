package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

// Struct para representar um produto
type Produto struct {
	ID     int
	Nome   string
	Preco  float64
	Estoque int
}

// Struct para o gerenciador de inventário
type GerenciadorInventario struct {
	Produtos    map[int]Produto
	Vendas      []Venda
	TotalVendas float64
}

// Struct para registrar vendas
type Venda struct {
	ID        int
	ProdutoID int
	Quantidade int
	Data      time.Time
	Total     float64
}

func main() {
	fmt.Println("=== SISTEMA DE GERENCIAMENTO DE INVENTÁRIO ===")
	
	// Inicializar gerenciador
	gerenciador := inicializarGerenciador()
	
	// Adicionar alguns produtos iniciais
	gerenciador.adicionarProduto(1, "Notebook Dell", 3500.00, 10)
	gerenciador.adicionarProduto(2, "Mouse Logitech", 120.50, 25)
	gerenciador.adicionarProduto(3, "Teclado Mecânico", 280.00, 15)
	gerenciador.adicionarProduto(4, "Monitor 24\"", 890.00, 8)
	
	// Mostrar inventário inicial
	gerenciador.mostrarInventario()
	
	// Realizar algumas vendas
	fmt.Println("\n--- REALIZANDO VENDAS ---")
	gerenciador.realizarVenda(1, 2)  // Vende 2 notebooks
	gerenciador.realizarVenda(2, 5)  // Vende 5 mouses
	gerenciador.realizarVenda(3, 3)  // Vende 3 teclados
	
	// Tentar vender produto sem estoque
	gerenciador.realizarVenda(4, 10) // Tenta vender 10 monitores (só tem 8)
	
	// Mostrar inventário atualizado
	gerenciador.mostrarInventario()
	
	// Mostrar relatório de vendas
	gerenciador.gerarRelatorioVendas()
	
	// Buscar produto específico
	fmt.Println("\n--- BUSCA DE PRODUTO ---")
	produto := gerenciador.buscarProduto(2)
	if produto.ID != 0 {
		fmt.Printf("Produto encontrado: %s - R$%.2f\n", produto.Nome, produto.Preco)
	}
	
	// Atualizar preço de um produto
	gerenciador.atualizarPreco(3, 310.00)
	
	// Mostrar inventário final
	gerenciador.mostrarInventario()
	
	// Calcular valor total do inventário
	valorTotal := gerenciador.calcularValorTotalInventario()
	fmt.Printf("\nValor total do inventário: R$%.2f\n", valorTotal)
}

// Função para inicializar o gerenciador
func inicializarGerenciador() *GerenciadorInventario {
	return &GerenciadorInventario{
		Produtos:    make(map[int]Produto),
		Vendas:      make([]Venda, 0),
		TotalVendas: 0.0,
	}
}

// Método para adicionar produto
func (g *GerenciadorInventario) adicionarProduto(id int, nome string, preco float64, estoque int) {
	if _, existe := g.Produtos[id]; existe {
		fmt.Printf("Produto com ID %d já existe!\n", id)
		return
	}
	
	produto := Produto{
		ID:      id,
		Nome:    nome,
		Preco:   preco,
		Estoque: estoque,
	}
	
	g.Produtos[id] = produto
	fmt.Printf("Produto '%s' adicionado com sucesso!\n", nome)
}

// Método para realizar venda
func (g *GerenciadorInventario) realizarVenda(produtoID int, quantidade int) {
	produto, existe := g.Produtos[produtoID]
	if !existe {
		fmt.Printf("Produto ID %d não encontrado!\n", produtoID)
		return
	}
	
	if produto.Estoque < quantidade {
		fmt.Printf("Estoque insuficiente! Disponíveis: %d, Solicitados: %d\n", 
			produto.Estoque, quantidade)
		return
	}
	
	// Atualizar estoque
	produto.Estoque -= quantidade
	g.Produtos[produtoID] = produto
	
	// Registrar venda
	totalVenda := produto.Preco * float64(quantidade)
	venda := Venda{
		ID:        len(g.Vendas) + 1,
		ProdutoID: produtoID,
		Quantidade: quantidade,
		Data:      time.Now(),
		Total:     totalVenda,
	}
	
	g.Vendas = append(g.Vendas, venda)
	g.TotalVendas += totalVenda
	
	fmt.Printf("Venda realizada: %d x %s = R$%.2f\n", 
		quantidade, produto.Nome, totalVenda)
}

// Método para mostrar inventário
func (g *GerenciadorInventario) mostrarInventario() {
	fmt.Println("\n=== INVENTÁRIO ATUAL ===")
	fmt.Printf("%-5s %-20s %-10s %-8s\n", "ID", "Nome", "Preço", "Estoque")
	fmt.Println("-------------------------------------------------")
	
	for _, produto := range g.Produtos {
		fmt.Printf("%-5d %-20s R$%-9.2f %-8d\n", 
			produto.ID, produto.Nome, produto.Preco, produto.Estoque)
	}
}

// Método para gerar relatório de vendas
func (g *GerenciadorInventario) gerarRelatorioVendas() {
	fmt.Println("\n=== RELATÓRIO DE VENDAS ===")
	fmt.Printf("Total de vendas realizadas: %d\n", len(g.Vendas))
	fmt.Printf("Valor total em vendas: R$%.2f\n", g.TotalVendas)
	
	if len(g.Vendas) > 0 {
		fmt.Println("\nDetalhes das vendas:")
		for _, venda := range g.Vendas {
			produto := g.Produtos[venda.ProdutoID]
			fmt.Printf("Venda #%d: %d x %s - R$%.2f em %s\n",
				venda.ID, venda.Quantidade, produto.Nome, 
				venda.Total, venda.Data.Format("02/01/2006 15:04"))
		}
	}
}

// Método para buscar produto
func (g *GerenciadorInventario) buscarProduto(id int) Produto {
	produto, existe := g.Produtos[id]
	if !existe {
		return Produto{} // Retorna struct vazia
	}
	return produto
}

// Método para atualizar preço
func (g *GerenciadorInventario) atualizarPreco(id int, novoPreco float64) {
	produto, existe := g.Produtos[id]
	if !existe {
		fmt.Printf("Produto ID %d não encontrado!\n", id)
		return
	}
	
	precoAntigo := produto.Preco
	produto.Preco = novoPreco
	g.Produtos[id] = produto
	
	fmt.Printf("Preço do produto '%s' atualizado: R$%.2f -> R$%.2f\n",
		produto.Nome, precoAntigo, novoPreco)
}

// Método para calcular valor total do inventário
func (g *GerenciadorInventario) calcularValorTotalInventario() float64 {
	total := 0.0
	for _, produto := range g.Produtos {
		total += produto.Preco * float64(produto.Estoque)
	}
	return total
}