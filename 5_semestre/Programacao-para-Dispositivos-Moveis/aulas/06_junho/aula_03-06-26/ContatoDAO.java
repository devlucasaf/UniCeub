package com.example.contatosdb.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.contatosdb.model.Contato;

import java.util.Collections;
import java.util.List;

public class ContatoDAO implements IContatoDAO{
    private final String TABELA_EMISSORES = "Contato";
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public ContatoDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        escrever = dbHelper.getWritableDatabase();
        ler = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Contato contato) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeContato", contato.getNomeContato());
        contentValues.put("telefoneContato", contato.getTelefoneContato());

        try {
            escrever.insert(DbHelper.TABELA_CONTATO, null, contentValues);
            Log.i("InfoDB", "Sucesso ao salvar o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao salvar o registro");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Contato contato) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeContato", contato.getNomeContato());
        contentValues.put("telefoneContato", contato.getTelefoneContato());

        try {
            String[] args = {contato.getId().toString()};
            escrever.update(DbHelper.TABELA_CONTATO, contentValues, "id=?", args);
            Log.i("InfoDB", "Sucesso ao alterar o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao alterar o registro");
            return false;
        }
        return true;
    }

    @Override
    public boolean excluir(Contato contato) {
        try {
            String[] args = {contato.getId().toString()};
            escrever.delete(DbHelper.TABELA_CONTATO, "id=?", args);
            Log.i("InfoDB", "Sucesso ao excluir o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao excluir o registro");
            return false;
        }
        return true;
    }

    @Override
    public List<Contato> listar(Contato contato) {
        return Collections.emptyList();
    }
}
