package com.unitask.app.dao;

import com.unitask.app.models.Disciplina;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface DisciplinaDao {

    @Insert
    long inserir(Disciplina disciplina);

    @Update
    void atualizar(Disciplina disciplina);

    @Delete
    void deletar(Disciplina disciplina);

    @Query("SELECT * FROM disciplinas WHERE usuarioId = :usuarioId ORDER BY nome ASC")
    List<Disciplina> listarPorUsuario(int usuarioId);

    @Query("SELECT * FROM disciplinas WHERE id = :id LIMIT 1")
    Disciplina buscarPorId(int id);

    @Query("SELECT COUNT(*) FROM disciplinas WHERE usuarioId = :usuarioId")
    int contarPorUsuario(int usuarioId);

    @Query("SELECT * FROM disciplinas WHERE usuarioId = :usuarioId AND nome LIKE '%' || :busca || '%' ORDER BY nome ASC")
    List<Disciplina> buscarPorNome(int usuarioId, String busca);

    @Query("SELECT * FROM disciplinas WHERE usuarioId = :usuarioId AND periodo = :periodo ORDER BY nome ASC")
    List<Disciplina> filtrarPorPeriodo(int usuarioId, int periodo);
}
