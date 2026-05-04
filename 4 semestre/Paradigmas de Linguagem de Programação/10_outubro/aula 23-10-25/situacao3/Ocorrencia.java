package situacao3;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Ocorrencia {
    private int             numero;
    private Date            data;
    private String          local;
    private String          descricao;

    private List<Viatura>   viaturas;   
    private List<Suspeito>  suspeitos; 
    private List<Policial>  policiais; 

    public Ocorrencia(int numero, Date data, String local, String descricao) {
        this.numero = numero;
        this.data = data;
        this.local = local;
        this.descricao = descricao;
        this.viaturas = new ArrayList<>();
        this.suspeitos = new ArrayList<>();
        this.policiais = new ArrayList<>();
    }

    public void registrar() {
        System.out.println("Ocorrência nº " + numero + " registrada com sucesso!");
    }

    public void encerrar() {
        System.out.println("Ocorrência nº " + numero + " encerrada.");
    }

    public int getNumero() { 
        return numero; 
    }

    public String getLocal() { 
        return local; 
    }

    public String getDescricao() { 
        return descricao; 
    }

    public void adicionarViatura(Viatura viatura) { 
        viaturas.add(viatura); 
    }
    
    public void adicionarSuspeito(Suspeito suspeito) { 
        suspeitos.add(suspeito); 
    }

    public void adicionarPolicial(Policial policial) { 
        policiais.add(policial); 
    }
}