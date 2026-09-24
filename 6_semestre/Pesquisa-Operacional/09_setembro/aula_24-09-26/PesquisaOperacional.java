import java.util.Locale;

// --- PESQUISA OPERACIONAL - AULA 24/09/26 ---
// --- MODELO ---
// --- Z = xa + xb + 2xc + 2xd ---
// --- IMPRESSÃO:  xa + 2xb + 3xc + 3xd <= 15000 ---
// --- CORTE:     2xa + 4xb +  xc + 3xd <= 20000 ---
// --- DOBRAR:    3xa + 2xb + 5xc + 3xd <= 20000 ---
// --- XA..XD:    xa, xb, xc, xd >= 1000 ---
public class PesquisaOperacional {

    private static final String[] VARIAVEIS   = {"xa", "xb", "xc", "xd"};
    private static final double[] COEF_Z      = {1, 1, 2, 2};
    private static final String[] RESTRICOES  = {"IMPRESSÃO", "CORTE", "DOBRAR"};
    private static final double[][] A         = {
            {1, 2, 3, 3},
            {2, 4, 1, 3},
            {3, 2, 5, 3}
    };
    private static final double[] LIMITE      = {15000, 20000, 20000};
    private static final double[] MINIMO      = {1000, 1000, 1000, 1000};

    private static final double[] VALORES_PLANILHA = {1500, 1000, 1000, 2833};

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));

        System.out.println("=== PLANILHA1 (valores atuais) ===");
        avaliar(VALORES_PLANILHA);

        System.out.println("\n=== SOLVER - MAXIMIZAR Z ===");
        imprimir(resolver(Sentido.MAXIMIZAR));

        System.out.println("\n=== SOLVER - MINIMIZAR Z (cenário do Relatório de Sensibilidade) ===");
        imprimir(resolver(Sentido.MINIMIZAR));
    }

    private static void avaliar(double[] x) {
        System.out.printf("Z = %.2f%n", produto(COEF_Z, x));
        for (int i = 0; i < RESTRICOES.length; i++) {
            double uso = produto(A[i], x);
            System.out.printf("%-10s %10.2f <= %8.0f  %s%n",
                    RESTRICOES[i], uso, LIMITE[i], uso <= LIMITE[i] ? "OK" : "VIOLADA");
        }

        for (int j = 0; j < VARIAVEIS.length; j++) {
            System.out.printf("%-10s %10.2f >= %8.0f  %s%n",
                    VARIAVEIS[j].toUpperCase(), x[j], MINIMO[j], x[j] >= MINIMO[j] ? "OK" : "VIOLADA");
        }
    }

    private static Resultado resolver(Sentido sentido) {
        int m = A.length;
        int n = COEF_Z.length;
        double fator = sentido == Sentido.MAXIMIZAR ? 1 : -1;

        double[][] t = new double[m + 1][n + m + 1];
        int[] base = new int[m];
        for (int i = 0; i < m; i++) {
            double b = LIMITE[i] - produto(A[i], MINIMO);
            if (b < 0) {
                throw new IllegalStateException("Problema inviável: mínimos excedem " + RESTRICOES[i]);
            }
            System.arraycopy(A[i], 0, t[i], 0, n);
            t[i][n + i] = 1;
            t[i][n + m] = b;
            base[i] = n + i;
        }

        for (int j = 0; j < n; j++) {
            t[m][j] = -fator * COEF_Z[j];
        }

        while (true) {
            int col = -1;
            for (int j = 0; j < n + m; j++) {
                if (t[m][j] < -1e-9) {
                    col = j;
                    break;
                }
            }

            if (col == -1) {
                break;
            }

            int lin = -1;
            double menor = Double.POSITIVE_INFINITY;
            for (int i = 0; i < m; i++) {
                if (t[i][col] > 1e-9) {
                    double razao = t[i][n + m] / t[i][col];
                    if (razao < menor - 1e-9 || (Math.abs(razao - menor) <= 1e-9 && base[i] < base[lin])) {
                        menor = razao;
                        lin = i;
                    }
                }
            }
            if (lin == -1) {
                throw new IllegalStateException("Problema ilimitado");
            }
            pivotear(t, lin, col);
            base[lin] = col;
        }

        Resultado resultado = new Resultado();
        resultado.x = MINIMO.clone();
        for (int i = 0; i < m; i++) {
            if (base[i] < n) {
                resultado.x[base[i]] += t[i][n + m];
            }
        }
        resultado.z = produto(COEF_Z, resultado.x);

        resultado.usoRestricao = new double[m];
        resultado.folga = new double[m];
        resultado.precoSombraRestricao = new double[m];
        for (int i = 0; i < m; i++) {
            resultado.usoRestricao[i] = produto(A[i], resultado.x);
            resultado.folga[i] = LIMITE[i] - resultado.usoRestricao[i];
            resultado.precoSombraRestricao[i] = fator * t[m][n + i];
        }

        resultado.precoSombraMinimo = new double[n];
        for (int j = 0; j < n; j++) {
            resultado.precoSombraMinimo[j] = -fator * t[m][j];
        }
        return resultado;
    }

    private static void pivotear(double[][] t, int lin, int col) {
        double p = t[lin][col];
        for (int j = 0; j < t[lin].length; j++) {
            t[lin][j] /= p;
        }

        for (int i = 0; i < t.length; i++) {
            if (i == lin || t[i][col] == 0) {
                continue;
            }

            double f = t[i][col];
            for (int j = 0; j < t[i].length; j++) {
                t[i][j] -= f * t[lin][j];
            }
        }
    }

    private static void imprimir(Resultado r) {
        System.out.printf("Z ótimo = %.4f%n%n", r.z);

        System.out.println("Células Variáveis");
        System.out.printf("%-6s %12s%n", "Nome", "Valor Final");
        for (int j = 0; j < VARIAVEIS.length; j++) {
            System.out.printf("%-6s %12.4f%n", VARIAVEIS[j], r.x[j]);
        }

        System.out.println("\nRestrições");
        System.out.printf("%-10s %12s %10s %12s %10s%n", "Nome", "Valor Final", "Folga", "Preço Sombra", "Lado R.H.");
        for (int i = 0; i < RESTRICOES.length; i++) {
            System.out.printf("%-10s %12.4f %10.4f %12.4f %10.0f%n",
                    RESTRICOES[i], r.usoRestricao[i], limpar(r.folga[i]), limpar(r.precoSombraRestricao[i]), LIMITE[i]);
        }

        for (int j = 0; j < VARIAVEIS.length; j++) {
            System.out.printf("%-10s %12.4f %10.4f %12.4f %10.0f%n",
                    VARIAVEIS[j].toUpperCase(), r.x[j], r.x[j] - MINIMO[j], limpar(r.precoSombraMinimo[j]), MINIMO[j]);
        }
    }

    private static double produto(double[] a, double[] b) {
        double s = 0;
        for (int i = 0; i < a.length; i++) {
            s += a[i] * b[i];
        }

        return s;
    }

    private static double limpar(double v) {
        return Math.abs(v) < 1e-6 ? 0 : v;
    }
}

