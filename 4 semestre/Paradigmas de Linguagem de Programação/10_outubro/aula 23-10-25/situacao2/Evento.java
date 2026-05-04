package situacao2;

import java.util.Date;

public class Evento {
    private String  nome;
    private String  local;
    private Date    data;

    public Evento(String nome, String local, Date data) {
        this.nome = nome;
        this.local = local;
        this.data = data;
    }

    public void abrirPortoes() {
        System.out.println("Portões abertos para o evento: " + nome);
    }

    public void encerrarShow() {
        System.out.println("Show encerrado: " + nome);
    }

    public String getNome() { 
        return nome; 
    }

    public String getLocal() { 
        return local; 
    }

    public Date getData() { 
        return data; 
    }
}