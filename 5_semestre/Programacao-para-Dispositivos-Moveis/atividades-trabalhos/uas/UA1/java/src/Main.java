public class Main {
    public static void main(String[] args) {

        Produto produto1 = new Produto(1, "Teclado", 19.9, 2);
        System.out.println(produto1);

        Produto produto2 = new Produto(2, "Mouse", 25.5, 3);
        System.out.println(produto2);

        // Alterando valores
        produto1.setQuantidade(5);
        produto1.setPreco(20.0);

        System.out.println("Após alteração:");
        System.out.println(produto1);
    }
}