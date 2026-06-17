package com.example.contatosdb.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int       VERSION = 1;
    public static String    NOME_DB = "DB_SEUBANCO";
    public static String    TABELA_CONTATO = "contato";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_CONTATO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nomeContato TEXT NOT NULL, " +
                " telefoneContato TEXT);";
        try {
            db.execSQL(sql);
            Log.i("InfoDB", "Tabela criada com sucesso");
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao criar a tabela");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_CONTATO + ";";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("InfoDB", "Tabela recriada com sucesso");
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao recriar a tabela");
        }
    }
}
