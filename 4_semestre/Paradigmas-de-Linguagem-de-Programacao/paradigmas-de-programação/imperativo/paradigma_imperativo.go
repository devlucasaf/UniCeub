package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

type Produto struct {
	ID     int
	Nome   string
	Preco  float64
	Estoque int
}

type GerenciadorInventario struct {
	Produtos    map[int]Produto
	Vendas      []Venda
	TotalVendas float64
}

type Venda struct {
	ID        int
	ProdutoID int
	Quantidade int
	Data      time.Time
	Total     float64
}

func main() {
	fmt.Println("=== SISTEMA DE GERENCIAMENTO DE INVENTÁRIO ===")
	
	gerenciador := inicializarGerenciador()
	
	gerenciador.adicionarProduto(1, "Notebook Dell", 3500.00, 10)
	gerenciador.adicionarProduto(2, "Mouse Logitech", 120.50, 25)
	gerenciador.adicionarProduto(3, "Teclado Mecânico", 280.00, 15)
	gerenciador.adicionarProduto(4, "Monitor 24\"", 890.00, 8)
	
	gerenciador.mostrarInventario()
	
	fmt.Println("\n--- REALIZANDO VENDAS ---")
	gerenciador.realizarVenda(1, 2)  
	gerenciador.realizarVenda(2, 5)  
	gerenciador.realizarVenda(3, 3)  
	
	gerenciador.realizarVenda(4, 10) 
	
	gerenciador.mostrarInventario()
	
	gerenciador.gerarRelatorioVendas()
	
	fmt.Println("\n--- BUSCA DE PRODUTO ---")
	produto := gerenciador.buscarProduto(2)
	if produto.ID != 0 {
		fmt.Printf("Produto encontrado: %s - R$%.2f\n", produto.Nome, produto.Preco)
	}
	
	gerenciador.atualizarPreco(3, 310.00)
	
	gerenciador.mostrarInventario()
	
	valorTotal := gerenciador.calcularValorTotalInventario()
	fmt.Printf("\nValor total do inventário: R$%.2f\n", valorTotal)
}

func inicializarGerenciador() *GerenciadorInventario {
	return &GerenciadorInventario{
		Produtos:    make(map[int]Produto),
		Vendas:      make([]Venda, 0),
		TotalVendas: 0.0,
	}
}

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
	
	produto.Estoque -= quantidade
	g.Produtos[produtoID] = produto
	
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

func (g *GerenciadorInventario) mostrarInventario() {
	fmt.Println("\n=== INVENTÁRIO ATUAL ===")
	fmt.Printf("%-5s %-20s %-10s %-8s\n", "ID", "Nome", "Preço", "Estoque")
	fmt.Println("-------------------------------------------------")
	
	for _, produto := range g.Produtos {
		fmt.Printf("%-5d %-20s R$%-9.2f %-8d\n", 
			produto.ID, produto.Nome, produto.Preco, produto.Estoque)
	}
}

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

func (g *GerenciadorInventario) buscarProduto(id int) Produto {
	produto, existe := g.Produtos[id]
	if !existe {
		return Produto{}
	}

	return produto
}

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

func (g *GerenciadorInventario) calcularValorTotalInventario() float64 {
	total := 0.0
	for _, produto := range g.Produtos {
		total += produto.Preco * float64(produto.Estoque)
	}
	return total
}
