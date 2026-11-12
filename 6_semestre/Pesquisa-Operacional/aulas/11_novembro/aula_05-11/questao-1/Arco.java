public record Arco(String origem, String destino, double custo) {
    String nome() {
        return "x_" + origem + "_" + destino;
    }
}
