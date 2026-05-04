package poo;

public abstract class Person {
    private final UUID  id;
    private String      name;
    private String      email;
    private LocalDate   createdAt;

    protected Person(String name, String email) {
        this.id = UUID.randomUUID();
        
        setName(name);
        setEmail(email);
        
        this.createdAt = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        
        this.name = name.trim();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String summary() {
        return getClass().getSimpleName() + " | " + name + " | " + email + " | id=" + id;
    }
}