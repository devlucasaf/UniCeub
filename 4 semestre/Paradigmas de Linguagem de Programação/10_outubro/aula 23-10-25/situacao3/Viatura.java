package situacao3;

import java.util.ArrayList;

public class Viatura {
    private String placa;
    private String modelo;
    private String estado;

    private List<Ocorrencia> ocorrencias; 

    public Viatura(String placa, String modelo, String estado) {
        this.placa = placa;
        this.modelo = modelo;
        this.estado = estado;
        this.ocorrencias = new ArrayList<>();
    }

    public void deslocar(String local) {
        System.out.println("Viatura " + placa + " deslocando-se para " + local);
    }

    public String getPlaca() { 
        return placa; 
    }
}