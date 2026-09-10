import java.util.Locale;

public class ProblemaTransporte {

    private static final Locale BR = Locale.forLanguageTag("pt-BR");

    private final String[]      fabricas;
    private final String[]      destinos;
    private final double[]      producaoMaxima;
    private final double[]      demanda;
    private final double[][]    custo;

    private double[][]          x;
    private SolverSimplex       solver;

    public ProblemaTransporte(String[] fabricas, String[] destinos, double[] producaoMaxima,
                              double[] demanda, double[][] custo) {
        this.fabricas = fabricas;
        this.destinos = destinos;
        this.producaoMaxima = producaoMaxima;
        this.demanda = demanda;
        this.custo = custo;
        this.x = new double[fabricas.length][destinos.length];
    }

    public Status resolver() {
        int nf = fabricas.length;
        int nd = destinos.length;
        int nVar = nf * nd;
        int nRes = nf + nd;

        double[] c = new double[nVar];
        for (int i = 0; i < nf; i++)
            for (int j = 0; j < nd; j++)
                c[indice(i, j)] = custo[i][j];

        double[][] a = new double[nRes][nVar];
        Tipo[] tipos = new Tipo[nRes];
        double[] b = new double[nRes];

        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nd; j++) {
                a[i][indice(i, j)] = 1;
            }
            tipos[i] = Tipo.MENOR_IGUAL;
            b[i] = producaoMaxima[i];
        }

        for (int j = 0; j < nd; j++) {
            int r = nf + j;
            for (int i = 0; i < nf; i++) a[r][indice(i, j)] = 1;
            tipos[r] = Tipo.IGUAL;
            b[r] = demanda[j];
        }

        solver = new SolverSimplex(c, a, tipos, b, true);
        Status status = solver.resolver();

        if (status == Status.OTIMO) {
            double[] v = solver.getX();
            for (int i = 0; i < nf; i++) {
                for (int j = 0; j < nd; j++) {
                    x[i][j] = v[indice(i, j)];
                }
            }
        }
        return status;
    }

    public double totalEnviado(int fabrica) {
        double soma = 0;
        for (int j = 0; j < destinos.length; j++) {
            soma += x[fabrica][j];
        }
        return soma;
    }

    // --- FÓRMULA B18:B21 → =SOMA(H3:H5) ---
    public double totalRecebido(int destino) {
        double soma = 0;
        for (int i = 0; i < fabricas.length; i++) soma += x[i][destino];
        return soma;
    }

    public double funcaoObjetivo() {
        double z = 0;
        for (int i = 0; i < fabricas.length; i++) {
            for (int j = 0; j < destinos.length; j++) {
                z += custo[i][j] * x[i][j];
            }
        }
        return z;
    }

    public void imprimirResultados() {
        System.out.println("=== RESULTADOS (QUANTIDADE TRANSPORTADA) ===");
        System.out.printf("%-6s", "");
        for (String d : destinos) {
            System.out.printf("%10s", d);
        }
        System.out.println();
        for (int i = 0; i < fabricas.length; i++) {
            System.out.printf("%-6s", fabricas[i]);
            for (int j = 0; j < destinos.length; j++) {
                System.out.printf("%10s", num(x[i][j]));
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("=== RESTRIÇÕES ===");
        for (int i = 0; i < fabricas.length; i++) {
            System.out.printf("%-6s%10s  <=  %10s%n", fabricas[i], num(totalEnviado(i)), num(producaoMaxima[i]));
        }
        for (int j = 0; j < destinos.length; j++) {
            System.out.printf("%-6s%10s   =  %10s%n", destinos[j], num(totalRecebido(j)), num(demanda[j]));
        }

        System.out.println();
        System.out.println("=== FUNÇÃO OBJETIVO ===");
        System.out.println("Z = " + num(funcaoObjetivo()));
    }

    public void imprimirSensibilidade() {
        double[] reduzido = solver.getCustoReduzido();
        double[] sombra = solver.getPrecoSombra();

        System.out.println();
        System.out.println("=== RELATÓRIO DE SENSIBILIDADE: CÉLULAS VARIÁVEIS ===");
        System.out.printf("%-8s%12s%12s%14s%n", "Nome", "Valor", "C. Reduzido", "Coeficiente");
        for (int i = 0; i < fabricas.length; i++) {
            for (int j = 0; j < destinos.length; j++) {
                System.out.printf("%-8s%12s%12s%14s%n", fabricas[i] + " " + destinos[j],
                        num(x[i][j]), num(reduzido[indice(i, j)]), num(custo[i][j]));
            }
        }

        System.out.println();
        System.out.println("=== RELATÓRIO DE SENSIBILIDADE: RESTRIÇÕES ===");
        System.out.printf("%-8s%12s%12s%14s%n", "Nome", "Valor", "P. Sombra", "Lado Direito");
        for (int i = 0; i < fabricas.length; i++) {
            System.out.printf("%-8s%12s%12s%14s%n", fabricas[i],
                    num(totalEnviado(i)), num(sombra[i]), num(producaoMaxima[i]));
        }

        for (int j = 0; j < destinos.length; j++) {
            System.out.printf("%-8s%12s%12s%14s%n", destinos[j],
                    num(totalRecebido(j)), num(sombra[fabricas.length + j]), num(demanda[j]));
        }
    }

    private int indice(int fabrica, int destino) {
        return fabrica * destinos.length + destino;
    }

    private static String num(double v) {
        return String.format(BR, "%,.0f", v);
    }

    public double[][] getX() {
        return x;
    }
}
