package veiculo;

public class SistemaVeiculos {
    public static void main(String[] args) {
        Pessoa joao = new PessoaFisica("João", "123.456.789-00");
        Carro carroA = new Esportivo("Ferrari F8");

        carroA.setProprietario(joao);

        System.out.println("Proprietário do carro: " + carroA.getProprietario().getNome());
        System.out.println("Modelo do carro: " + carroA.getModelo());
        System.out.println("Registro Federal: " + carroA.getProprietario().obterRegistroFederal());
    }
}