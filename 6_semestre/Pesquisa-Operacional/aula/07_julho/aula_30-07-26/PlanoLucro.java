public class PlanoLucro {
    public double  decisao;
    public double  processo;
    public double  massa;
    public int     recheio;
    public int     fixo;
    public int     concorrencia;
    public int     custoIngredientes;
    public int     custoProcesso;
    public double  total;
    public int     receita;
    public int     lucro;

    public void definirValores(double decisao, double processo, double massa, int recheio, int fixo, int concorrencia) {
        this.decisao = 3.90;
        this.processo = 0.40;
        this.massa = 1.30;
        this.recheio = 2;
        this.fixo = 6000;
        this.concorrencia = 7;
    }

    private void calcularParametros(int demanda) {
        demanda = (int) Math.round(15000 - this.decisao * 5000 + this.concorrencia * 5000);
        custoIngredientes = (int) Math.round(demanda * (this.massa + this.recheio));
        custoProcesso = (int) Math.round(demanda * this.processo);
        total = custoProcesso + custoIngredientes + this.fixo;
        receita = (int) Math.round(demanda * this.decisao);
        lucro = (int) Math.round(receita - total);
    }

    public static void main(String[] args) {
        PlanoLucro plano = new PlanoLucro();
        plano.definirValores(3.90, 0.40, 1.30, 2, 6000, 7);
        plano.calcularParametros(0);

        System.out.println("Decisão: " + plano.decisao);
        System.out.println("Plano do Custo do Processo: " + plano.processo);
        System.out.println("Massa: " + plano.massa);
        System.out.println("Recheio: " + plano.recheio);
        System.out.println("Fixo: " + plano.fixo);
        System.out.println("Concorrência: " + plano.concorrencia);
        System.out.println("Custo dos Ingredientes: " + plano.custoIngredientes);
        System.out.println("Custo do Processo: " + plano.custoProcesso);
        System.out.println("Receita: " + plano.receita);
        System.out.println("Total: " + plano.total);
        System.out.println("Lucro: " + plano.lucro);
    }
}
