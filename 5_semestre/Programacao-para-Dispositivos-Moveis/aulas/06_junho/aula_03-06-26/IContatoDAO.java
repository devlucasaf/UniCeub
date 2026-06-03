package com.example.contatosdb.persistencia;

import com.example.contatosdb.model.Contato;

import java.util.List;

public interface IContatoDAO {
    public boolean          salvar(Contato contato);
    public boolean          atualizar(Contato contato);
    public boolean          excluir(Contato contato);
    public List<Contato>    listar(Contato contato);
}
