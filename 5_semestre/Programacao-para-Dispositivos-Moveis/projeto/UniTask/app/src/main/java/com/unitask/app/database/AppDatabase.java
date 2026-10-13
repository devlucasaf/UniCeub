package com.unitask.app.database;

import com.unitask.app.dao.AtividadeDao;
import com.unitask.app.dao.DisciplinaDao;
import com.unitask.app.dao.UsuarioDao;
import com.unitask.app.models.Atividade;
import com.unitask.app.models.Disciplina;
import com.unitask.app.models.Usuario;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
    entities = {Usuario.class, Disciplina.class, Atividade.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "unitask_db";
    private static volatile AppDatabase instance;

    public abstract UsuarioDao usuarioDao();
    public abstract DisciplinaDao disciplinaDao();
    public abstract AtividadeDao atividadeDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return instance;
    }
}
