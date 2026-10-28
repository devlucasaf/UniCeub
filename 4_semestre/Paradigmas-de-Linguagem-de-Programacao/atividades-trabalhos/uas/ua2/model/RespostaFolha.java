package UA2.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "folha_resposta")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespostaFolha {

    @XmlElement(name = "resultado")
    private List<ResultadoFolha> resultados;

    public RespostaFolha() {
    }

    public List<ResultadoFolha> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoFolha> resultados) {
        this.resultados = resultados;
    }
}
