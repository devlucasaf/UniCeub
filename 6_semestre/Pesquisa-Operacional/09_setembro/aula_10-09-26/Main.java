public class Main {

    public static void main(String[] args) {

        String[] fabricas = {"F1", "F2", "F3"};
        double[] producaoMaxima = {10000, 15000, 5000};

        String[] destinos = {"D1", "D2", "D3", "D4"};
        double[] demanda = {8000, 4000, 7000, 11000};

        double[][] custo = {
                {13, 8,  9, 12},
                {12, 9, 10, 14},
                { 8, 8,  9,  6}
        };

        ProblemaTransporte problema = new ProblemaTransporte(fabricas, destinos, producaoMaxima, demanda, custo);

        Status status = problema.resolver();

        if (status != Status.OTIMO) {
            System.out.println("O Solver não encontrou solução: " + status);
            return;
        }

        problema.imprimirResultados();
        problema.imprimirSensibilidade();
    }
}