public class MaximizacaoLucro {
    public static void main(String[] args) {

        double  lucroPorLote = 450.0;
        int     horasDisponiveis = 60;
        int     horasPorLote = 4;
        int     maxLotesContrato = 12;

        int maxLotesPorHoras = horasDisponiveis / horasPorLote;

        int lotesOtimos = Math.min(maxLotesPorHoras, maxLotesContrato);

        double lucroMaximo = lotesOtimos * lucroPorLote;

        System.out.println("Lotes a processar: " + lotesOtimos);
        System.out.println("Lucro máximo semanal: R$ " + lucroMaximo);
    }
}