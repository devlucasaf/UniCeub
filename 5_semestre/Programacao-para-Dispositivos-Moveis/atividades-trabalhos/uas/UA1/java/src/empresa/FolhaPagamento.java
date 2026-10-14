package empresa;

import java.util.HashMap;
import java.util.Map;

public class FolhaPagamento {
    private Map<String, Double> pagamentosRealizados;

    public FolhaPagamento() {
        this.pagamentosRealizados = new HashMap<>();
    }

    public void processarPagamento(Funcionario f) throws FuncionarioInativoException {
        if (!f.isAtivo()) {
            throw new FuncionarioInativoException("Funcionário " + f.getNome() + " está inativo.");
        }
        double valor = f.calcularSalarioTotal();
        pagamentosRealizados.put(f.getId(), valor);
        System.out.println("Pagamento de R$ " + String.format("%.2f", valor) + " para " + f.getNome());
    }

    public double totalPago() {
        return pagamentosRealizados.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public void limparRegistros() {
        pagamentosRealizados.clear();
    }
}