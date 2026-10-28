package UA2.service;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.JAXBContext;

import UA2.model.RequisicaoFolha;
import UA2.model.RespostaFolha;

import java.io.File;
import java.io.StringReader;

public class FolhaPagamentoService {

    public RespostaFolha processarFolha(File xmlEntrada, String wsUrl) throws Exception {
        JAXBContext context = JAXBContext.newInstance(
                RequisicaoFolha.class,
                RespostaFolha.class
        );

        RequisicaoFolha requisicao =
                (RequisicaoFolha) context.createUnmarshaller()
                        .unmarshal(xmlEntrada);

        Client client = ClientBuilder.newClient();

        String xmlResposta = client.target(wsUrl)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.xml(requisicao), String.class);

        RespostaFolha resposta =
                (RespostaFolha) context.createUnmarshaller()
                        .unmarshal(new StringReader(xmlResposta));

        return resposta;
    }
}
