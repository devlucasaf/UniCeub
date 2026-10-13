package com.unitask.app.dao;

import com.unitask.app.models.Atividade;
import com.unitask.app.models.AtividadeComDisciplina;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AtividadeDao {

    @Insert
    long inserir(Atividade atividade);

    @Update
    void atualizar(Atividade atividade);

    @Delete
    void deletar(Atividade atividade);

    @Query("SELECT * FROM atividades WHERE id = :id LIMIT 1")
    Atividade buscarPorId(int id);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId " +
           "ORDER BY a.dataEntrega ASC")
    List<AtividadeComDisciplina> listarPorUsuario(int usuarioId);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId AND a.disciplinaId = :disciplinaId " +
           "ORDER BY a.dataEntrega ASC")
    List<AtividadeComDisciplina> filtrarPorDisciplina(int usuarioId, int disciplinaId);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId AND a.prioridade = :prioridade " +
           "ORDER BY a.dataEntrega ASC")
    List<AtividadeComDisciplina> filtrarPorPrioridade(int usuarioId, String prioridade);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId AND a.situacao = :situacao " +
           "ORDER BY a.dataEntrega ASC")
    List<AtividadeComDisciplina> filtrarPorSituacao(int usuarioId, String situacao);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId AND a.situacao = 'PENDENTE' " +
           "ORDER BY a.dataEntrega ASC " +
           "LIMIT :limite")
    List<AtividadeComDisciplina> proximasAtividades(int usuarioId, int limite);

    @Query("SELECT COUNT(*) FROM atividades WHERE usuarioId = :usuarioId")
    int contarTotal(int usuarioId);

    @Query("SELECT COUNT(*) FROM atividades WHERE usuarioId = :usuarioId AND situacao = 'PENDENTE'")
    int contarPendentes(int usuarioId);

    @Query("SELECT COUNT(*) FROM atividades WHERE usuarioId = :usuarioId AND situacao = 'CONCLUIDA'")
    int contarConcluidas(int usuarioId);

    @Query("SELECT COUNT(*) FROM atividades WHERE usuarioId = :usuarioId AND situacao = 'PENDENTE' AND dataEntrega < :agora")
    int contarAtrasadas(int usuarioId, long agora);

    @Query("SELECT * FROM atividades WHERE usuarioId = :usuarioId AND situacao = 'PENDENTE' AND notificacaoEnviada = 0")
    List<Atividade> buscarParaNotificar(int usuarioId);

    @Query("SELECT * FROM atividades WHERE disciplinaId = :disciplinaId")
    List<Atividade> listarPorDisciplina(int disciplinaId);

    @Query("SELECT a.*, d.nome AS disciplinaNome, d.cor AS disciplinaCor, d.professor AS disciplinaProfessor " +
           "FROM atividades a " +
           "INNER JOIN disciplinas d ON a.disciplinaId = d.id " +
           "WHERE a.usuarioId = :usuarioId AND " +
           "(:disciplinaId = 0 OR a.disciplinaId = :disciplinaId) AND " +
           "(:prioridade = '' OR a.prioridade = :prioridade) AND " +
           "(:situacao = '' OR a.situacao = :situacao) " +
           "ORDER BY a.dataEntrega ASC")
    List<AtividadeComDisciplina> filtrarCombinado(int usuarioId, int disciplinaId, String prioridade, String situacao);
}
