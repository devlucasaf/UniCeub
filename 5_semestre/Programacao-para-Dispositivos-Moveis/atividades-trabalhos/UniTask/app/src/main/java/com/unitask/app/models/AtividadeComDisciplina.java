package com.unitask.app.models;

public class AtividadeComDisciplina {

    private int     id;
    private int     usuarioId;
    private int     disciplinaId;
    private String  titulo;
    private String  descricao;
    private String  tipo;
    private String  prioridade;
    private String  situacao;
    private long    dataEntrega;
    private long    dataCadastro;
    private boolean notificacaoEnviada;
    private String  disciplinaNome;
    private String  disciplinaCor;
    private String  disciplinaProfessor;

    public AtividadeComDisciplina() {}

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
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

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    public String getDisciplinaCor() {
        return disciplinaCor;
    }

    public void setDisciplinaCor(String disciplinaCor) {
        this.disciplinaCor = disciplinaCor;
    }

    public String getDisciplinaProfessor() {
        return disciplinaProfessor;
    }

    public void setDisciplinaProfessor(String disciplinaProfessor) {
        this.disciplinaProfessor = disciplinaProfessor;
    }

    public boolean isAtrasada() {
        return !"CONCLUIDA".equals(situacao) && dataEntrega < System.currentTimeMillis();
    }

    public boolean isConcluida() {
        return "CONCLUIDA".equals(situacao);
    }

    public boolean isPendente() {
        return "PENDENTE".equals(situacao);
    }
}
