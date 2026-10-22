public class SolverSimplex {

    private static final double EPS = 1e-9;
    private static final int    MAX_ITERACOES = 100_000;
    private final double[]      custos;
    private final double[][]    a;
    private final Tipo[]        tipos;
    private final double[]      b;
    private final boolean       minimizar;
    private Status              status;
    private double[]            x;
    private double              valorObjetivo;
    private double[]            custoReduzido;
    private double[]            precoSombra;

    public SolverSimplex(double[] custos, double[][] a, Tipo[] tipos, double[] b, boolean minimizar) {
        this.custos = custos.clone();
        this.a = new double[a.length][];
        for (int i = 0; i < a.length; i++) {
            this.a[i] = a[i].clone();
        }
        this.tipos = tipos.clone();
        this.b = b.clone();
        this.minimizar = minimizar;
    }

    public Status resolver() {
        int m = b.length;
        int n = custos.length;

        double[][] linhas = new double[m][n];
        double[] rhs = new double[m];
        Tipo[] tp = new Tipo[m];
        double[] sinal = new double[m];

        for (int i = 0; i < m; i++) {
            sinal[i] = b[i] < 0 ? -1 : 1;
            for (int j = 0; j < n; j++) {
                linhas[i][j] = a[i][j] * sinal[i];
            }

            rhs[i] = b[i] * sinal[i];
            tp[i] = tipos[i];

            if (sinal[i] < 0 && tp[i] == Tipo.MENOR_IGUAL) {
                tp[i] = Tipo.MAIOR_IGUAL;
            } else if (sinal[i] < 0 && tp[i] == Tipo.MAIOR_IGUAL) {
                tp[i] = Tipo.MENOR_IGUAL;
            }
        }

        double[] c = new double[n];
        for (int j = 0; j < n; j++) {
            c[j] = minimizar ? custos[j] : -custos[j];
        }

        int nFolga = 0;
        int nArt = 0;
        for (Tipo t : tp) {
            if (t != Tipo.IGUAL) {
                nFolga++;
            }

            if (t != Tipo.MENOR_IGUAL) {
                nArt++;
            }
        }
        int total = n + nFolga + nArt;

        double[][] t = new double[m][total + 1];
        int[] base = new int[m];
        int[] colIdentidade = new int[m];
        boolean[] artificial = new boolean[total];
        int f = n;
        int art = n + nFolga;

        for (int i = 0; i < m; i++) {
            System.arraycopy(linhas[i], 0, t[i], 0, n);
            t[i][total] = rhs[i];
            switch (tp[i]) {
                case MENOR_IGUAL:
                    t[i][f] = 1;
                    base[i] = f;
                    colIdentidade[i] = f;
                    f++;
                    break;
                case MAIOR_IGUAL:
                    t[i][f++] = -1;
                    t[i][art] = 1;
                    artificial[art] = true;
                    base[i] = art;
                    colIdentidade[i] = art;
                    art++;
                    break;
                default:
                    t[i][art] = 1;
                    artificial[art] = true;
                    base[i] = art;
                    colIdentidade[i] = art;
                    art++;
            }
        }

        if (nArt > 0) {
            double[] c1 = new double[total];
            for (int j = 0; j < total; j++) {
                c1[j] = artificial[j] ? 1 : 0;
            }

            iterar(t, base, c1, null);
            if (valorAtual(t, base, c1) > 1e-7) {
                status = Status.INVIAVEL;
                return status;
            }

            for (int i = 0; i < m; i++) {
                if (!artificial[base[i]]) {
                    continue;
                }

                for (int j = 0; j < n + nFolga; j++) {
                    if (Math.abs(t[i][j]) > EPS) {
                        pivotar(t, base, i, j);
                        break;
                    }
                }
            }
        }

        double[] c2 = new double[total];
        System.arraycopy(c, 0, c2, 0, n);
        if (!iterar(t, base, c2, artificial)) {
            status = Status.ILIMITADO;
            return status;
        }

        x = new double[n];
        for (int i = 0; i < m; i++) {
            if (base[i] < n) {
                x[base[i]] = limpar(t[i][total]);
            }
        }

        valorObjetivo = 0;
        for (int j = 0; j < n; j++) {
            valorObjetivo += custos[j] * x[j];
        }

        double[] d = reduzidos(t, base, c2);
        custoReduzido = new double[n];
        for (int j = 0; j < n; j++) {
            custoReduzido[j] = limpar(minimizar ? d[j] : -d[j]);
        }

        precoSombra = new double[m];
        for (int i = 0; i < m; i++) {
            double y = 0;
            for (int k = 0; k < m; k++) {
                y += c2[base[k]] * t[k][colIdentidade[i]];
            }
            y *= sinal[i];
            precoSombra[i] = limpar(minimizar ? y : -y);
        }

        status = Status.OTIMO;
        return status;
    }

    private boolean iterar(double[][] t, int[] base, double[] c, boolean[] proibido) {
        int m = t.length;
        int rhs = t[0].length - 1;
        for (int it = 0; it < MAX_ITERACOES; it++) {
            double[] d = reduzidos(t, base, c);

            int entra = -1;
            for (int j = 0; j < rhs; j++) {
                if (proibido != null && proibido[j]) {
                    continue;
                }

                if (d[j] < -EPS) {
                    entra = j;
                    break;
                }
            }

            if (entra == -1) {
                return true;
            }

            int sai = -1;
            double melhor = Double.POSITIVE_INFINITY;
            for (int i = 0; i < m; i++) {
                if (t[i][entra] <= EPS) {
                    continue;
                }

                double razao = t[i][rhs] / t[i][entra];
                if (razao < melhor - EPS || (Math.abs(razao - melhor) <= EPS && base[i] < base[sai])) {
                    melhor = razao;
                    sai = i;
                }
            }

            if (sai == -1) {
                return false;
            }

            pivotar(t, base, sai, entra);
        }
        throw new IllegalStateException("Limite de iterações atingido");
    }

    private void pivotar(double[][] t, int[] base, int linha, int coluna) {
        double piv = t[linha][coluna];
        for (int j = 0; j < t[linha].length; j++) {
            t[linha][j] /= piv;
        }

        for (int i = 0; i < t.length; i++) {
            if (i == linha) {
                continue;
            }

            double fator = t[i][coluna];
            if (fator == 0) {
                continue;
            }

            for (int j = 0; j < t[i].length; j++) {
                t[i][j] -= fator * t[linha][j];
            }
        }
        base[linha] = coluna;
    }

    private double[] reduzidos(double[][] t, int[] base, double[] c) {
        int colunas = t[0].length - 1;
        double[] d = new double[colunas];
        for (int j = 0; j < colunas; j++) {
            double z = 0;
            for (int i = 0; i < t.length; i++) {
                z += c[base[i]] * t[i][j];
            }
            d[j] = c[j] - z;
        }
        return d;
    }

    private double valorAtual(double[][] t, int[] base, double[] c) {
        int rhs = t[0].length - 1;
        double v = 0;
        for (int i = 0; i < t.length; i++) {
            v += c[base[i]] * t[i][rhs];
        }
        return v;
    }

    private static double limpar(double v) {
        return Math.abs(v) < 1e-7 ? 0 : v;
    }

    public Status getStatus() {
        return status;
    }

    public double[] getX() {
        return x.clone();
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public double[] getCustoReduzido() {
        return custoReduzido.clone();
    }

    public double[] getPrecoSombra() {
        return precoSombra.clone();
    }
}