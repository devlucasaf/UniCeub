package com.unitask.app.dao;

import com.unitask.app.models.Usuario;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert
    long inserir(Usuario usuario);

    @Update
    void atualizar(Usuario usuario);

    @Delete
    void deletar(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    Usuario buscarPorEmail(String email);

    @Query("SELECT * FROM usuarios WHERE email = :email AND senha = :senha LIMIT 1")
    Usuario login(String email, String senha);

    @Query("SELECT * FROM usuarios WHERE id = :id LIMIT 1")
    Usuario buscarPorId(int id);

    @Query("SELECT COUNT(*) FROM usuarios WHERE email = :email")
    int emailExiste(String email);

    @Query("SELECT * FROM usuarios")
    List<Usuario> listarTodos();
}
