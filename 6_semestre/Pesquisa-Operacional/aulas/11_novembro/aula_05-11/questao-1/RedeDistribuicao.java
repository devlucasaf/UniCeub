import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.util.LinkedHashMap;
import java.util.Map;

public class RedeDistribuicao {

    public static void main(String[] args) {
        Loader.loadNativeLibraries();

        Map<String, Double> saldo = new LinkedHashMap<>();
        saldo.put("BA", -500.0);
        saldo.put("SP", -600.0);
        saldo.put("D",  -300.0); 
        saldo.put("MG",  200.0);
        saldo.put("RJ",  350.0);
        saldo.put("GO",  150.0);
        saldo.put("PR",  300.0);
        saldo.put("SC",  150.0);
        saldo.put("RS",  250.0);

        Arco[] arcos = {
            new Arco("BA", "GO", 40),
            new Arco("BA", "MG", 25),
            new Arco("BA", "RJ", 30),
            new Arco("MG", "GO", 20),
            new Arco("MG", "RJ", 20),
            new Arco("SP", "MG", 20),
            new Arco("SP", "RJ", 15),
            new Arco("SP", "PR", 20),
            new Arco("SP", "SC", 35),
            new Arco("SP", "RS", 50),
            new Arco("SC", "RS", 20),
            new Arco("D",  "MG",  0),
            new Arco("D",  "RJ",  0),
            new Arco("D",  "GO",  0),
            new Arco("D",  "PR",  0),
            new Arco("D",  "SC",  0),
            new Arco("D",  "RS",  0),
        };

        boolean inteiro = args.length > 0 && args[0].equalsIgnoreCase("inteiro");
        MPSolver solver = MPSolver.createSolver(inteiro ? "SCIP" : "GLOP");
        if (solver == null) {
            System.out.println("Solver não disponível.");
            return;
        }

        MPVariable[] x = new MPVariable[arcos.length];
        for (int i = 0; i < arcos.length; i++) {
            if (inteiro) {
                x[i] = solver.makeIntVar(0, Double.POSITIVE_INFINITY, arcos[i].nome());
            } else {
                x[i] = solver.makeNumVar(0, Double.POSITIVE_INFINITY, arcos[i].nome());
            }
        }

        MPObjective objetivo = solver.objective();
        for (int i = 0; i < arcos.length; i++) {
            objetivo.setCoefficient(x[i], arcos[i].custo());
        }
        objetivo.setMinimization();

        for (Map.Entry<String, Double> v : saldo.entrySet()) {
            MPConstraint c = solver.makeConstraint(v.getValue(), v.getValue(), "no_" + v.getKey());
            for (int i = 0; i < arcos.length; i++) {
                if (arcos[i].destino().equals(v.getKey())) {
                    c.setCoefficient(x[i], 1);
                }

                if (arcos[i].origem().equals(v.getKey())) {
                    c.setCoefficient(x[i], -1);
                }
            }
        }

        MPSolver.ResultStatus status = solver.solve();
        if (status != MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("Solução ótima não encontrada: " + status);
            return;
        }

        System.out.println("Modelo: " + (inteiro ? "programação inteira (SCIP)" : "programação linear (GLOP)"));
        System.out.printf("Custo mínimo total: %.2f%n%n", objetivo.value());
        System.out.printf("%-10s %-8s %8s %10s%n", "Arco", "Custo", "Qtd", "Subtotal");
        
        for (int i = 0; i < arcos.length; i++) {
            double q = x[i].solutionValue();
            System.out.printf("%-10s %-8.0f %8.0f %10.0f%n",
                    arcos[i].origem() + "->" + arcos[i].destino(),
                    arcos[i].custo(), q, q * arcos[i].custo());
        }
    }
}
