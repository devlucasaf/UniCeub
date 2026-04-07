package lojaexemplo;

import java.util.ArrayList;

public class Loja {

    private ArrayList<Produto> produtos;

    public Loja() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    public Produto buscarProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void removerProduto(int codigo) {
        Produto p = buscarProduto(codigo);
        if (p != null) {
            produtos.remove(p);
            System.out.println("Produto removido.");
        } 
        
        else {
            System.out.println("Produto não encontrado.");
        }
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.calcularTotal();
        }
        return total;
    }

    public Produto produtoMaisCaro() {
        if (produtos.isEmpty()) return null;

        Produto maisCaro = produtos.get(0);

        for (Produto p : produtos) {
            if (p.getPreco() > maisCaro.getPreco()) {
                maisCaro = p;
            }
        }
        return maisCaro;
    }
}