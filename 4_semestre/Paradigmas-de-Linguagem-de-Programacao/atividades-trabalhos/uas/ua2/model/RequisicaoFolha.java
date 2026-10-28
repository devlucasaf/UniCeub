package UA2.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "folha_requisicao")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequisicaoFolha {

    @XmlElement(name = "funcionario")
    private List<Funcionario> funcionarios;

    public RequisicaoFolha() {
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
