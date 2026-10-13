package com.unitask.app.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "atividades", foreignKeys = {
        @ForeignKey(entity = Disciplina.class, parentColumns = "id", childColumns = "disciplinaId",
            onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "usuarioId",
            onDelete = ForeignKey.CASCADE)
    },
    indices = {@Index("disciplinaId"), @Index("usuarioId")}
)
public class Atividade {

    @PrimaryKey(autoGenerate = true)
    private int     id;

    private int     usuarioId;

    private int     disciplinaId;

    @NonNull
    private String  titulo;

    private String  descricao;

    @NonNull
    private String  tipo;

    @NonNull
    private String  prioridade;

    @NonNull
    private String  situacao;

    private long    dataEntrega;

    private long    dataCadastro;

    private boolean notificacaoEnviada;

    public Atividade() {}

    public Atividade(int usuarioId, int disciplinaId, @NonNull String titulo, String descricao,
             @NonNull String tipo, @NonNull String prioridade, @NonNull String situacao, long dataEntrega) {
        this.usuarioId = usuarioId;
        this.disciplinaId = disciplinaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.situacao = situacao;
        this.dataEntrega = dataEntrega;
        this.dataCadastro = System.currentTimeMillis();
        this.notificacaoEnviada = false;
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

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(@NonNull String prioridade) {
        this.prioridade = prioridade;
    }

    @NonNull
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(@NonNull String situacao) {
        this.situacao = situacao;
    }

    public long getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(long dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public long getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(long dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isNotificacaoEnviada() {
        return notificacaoEnviada;
    }

    public void setNotificacaoEnviada(boolean notificacaoEnviada) {
        this.notificacaoEnviada = notificacaoEnviada;
    }

    public boolean isAtrasada() {
        return !situacao.equals("CONCLUIDA") && dataEntrega < System.currentTimeMillis();
    }

    public boolean isConcluida() {
        return situacao.equals("CONCLUIDA");
    }

    public boolean isPendente() {
        return situacao.equals("PENDENTE");
    }
}
