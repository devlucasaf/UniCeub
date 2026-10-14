package poojava;

import java.time.LocalDate;
import java.util.*;

public abstract class Pessoa {
    private final UUID  id;
    private String      nome;
    private String      email;
    private LocalDate   dataCriacao;

    protected Pessoa(String nome, String email) {
        this.id = UUID.randomUUID();
        
        setNome(nome);
        setEmail(email);
        
        this.dataCriacao = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public final String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        
        this.nome = nome.trim();
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        
        this.email = email.trim().toLowerCase(Locale.ROOT);
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String resumo() {
        return getClass().getSimpleName() + " | " + nome + " | " + email + " | id=" + id;
    }
}