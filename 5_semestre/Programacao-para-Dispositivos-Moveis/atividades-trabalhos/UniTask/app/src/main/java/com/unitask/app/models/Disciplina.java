package com.unitask.app.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(
    tableName = "disciplinas",
    foreignKeys = @ForeignKey(
        entity = Usuario.class,
        parentColumns = "id",
        childColumns = "usuarioId",
        onDelete = ForeignKey.CASCADE
    ),
    indices = {@Index("usuarioId")}
)
public class Disciplina {

    @PrimaryKey(autoGenerate = true)
    private int     id;

    private int     usuarioId;

    @NonNull
    private String  nome;

    private String  professor;

    private String  cor;

    private int     periodo;

    private long    dataCadastro;

    public Disciplina() {}

    public Disciplina(int usuarioId, @NonNull String nome, String professor, String cor, int periodo) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.professor = professor;
        this.cor = cor;
        this.periodo = periodo;
        this.dataCadastro = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public long getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(long dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return nome;
    }
}
