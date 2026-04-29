using System;
using System.Collections.Generic;

namespace LojaExemplo
{
    public class Loja
    {
        private List<Produto> produtos;

        public Loja()
        {
            produtos = new List<Produto>();
        }

        public void AdicionarProduto(Produto p)
        {
            produtos.Add(p);
            Console.WriteLine("Produto adicionado com sucesso!");
        }

        public void ListarProdutos()
        {
            if (produtos.Count == 0)
            {
                Console.WriteLine("Nenhum produto cadastrado.");
                return;
            }

            foreach (var p in produtos)
            {
                Console.WriteLine(p);
            }
        }

        public Produto BuscarProduto(int codigo)
        {
            return produtos.Find(p => p.Codigo == codigo);
        }

        public void RemoverProduto(int codigo)
        {
            Produto p = BuscarProduto(codigo);
            if (p != null)
            {
                produtos.Remove(p);
                Console.WriteLine("Produto removido.");
            }
            else
            {
                Console.WriteLine("Produto não encontrado.");
            }
        }

        public double CalcularValorTotalEstoque()
        {
            double total = 0;
            foreach (var p in produtos)
            {
                total += p.CalcularTotal();
            }
            return total;
        }

        public Produto ProdutoMaisCaro()
        {
            if (produtos.Count == 0) return null;

            Produto maisCaro = produtos[0];
            foreach (var p in produtos)
            {
                if (p.Preco > maisCaro.Preco)
                {
                    maisCaro = p;
                }
            }
            return maisCaro;
        }
    }
}
``