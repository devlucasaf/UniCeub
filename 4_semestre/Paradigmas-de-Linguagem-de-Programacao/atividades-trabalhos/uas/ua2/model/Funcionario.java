package UA2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Funcionario {

    private String  nome;
    private String  matricula;
    private int     diasTrabalhados;
    private int     horasExtras;
    private int     diasFerias;

    public Funcionario() {
    }

    public Funcionario(String nome, String matricula, int diasTrabalhados, int horasExtras, int diasFerias) {
        this.nome = nome;
        this.matricula = matricula;
        this.diasTrabalhados = diasTrabalhados;
        this.horasExtras = horasExtras;
        this.diasFerias = diasFerias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public int getDiasFerias() {
        return diasFerias;
    }

    public void setDiasFerias(int diasFerias) {
        this.diasFerias = diasFerias;
    }
}
