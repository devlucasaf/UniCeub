package UA2;

import UA2.model.RespostaFolha;
import UA2.service.FolhaPagamentoService;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {

            FolhaPagamentoService service =
                    new FolhaPagamentoService();

            RespostaFolha resultado =
                    service.processarFolha(
                            new File("funcionarios.xml"),
                            "https://api.exemplo.com/calcula_folha"
                    );

            resultado.getResultados().forEach(r -> {

                System.out.println("================================");
                System.out.println("Matrícula: " + r.getMatricula());
                System.out.println("Salário Bruto: R$ " + r.getSalarioBruto());
                System.out.println("Descontos: R$ " + r.getDescontos());
                System.out.println("Salário Líquido: R$ " + r.getSalarioLiquido());
            });

        } catch (Exception e) {

            System.out.println("Erro ao processar folha:");
            e.printStackTrace();
        }
    }
}
