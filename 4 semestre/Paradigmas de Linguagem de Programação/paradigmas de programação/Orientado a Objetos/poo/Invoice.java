package poo;

public class Invoice {
    private final UUID      id;
    private final Student   student;
    private final int       installmentCount;
    private final double    totalAmount;
    private double          paidAmount;
    private final LocalDate createdAt;

    public Invoice(Student student, int installmentCount, double totalAmount) {
        this.id = UUID.randomUUID();
        this.student = Objects.requireNonNull(student);
        
        if (installmentCount <= 0) {
            throw new IllegalArgumentException("Parcelas inválidas");
        }
        
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.installmentCount = installmentCount;
        this.totalAmount = totalAmount;
        this.paidAmount = 0.0;
        this.createdAt = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public double remaining() {
        return Math.max(0.0, totalAmount - paidAmount);
    }

    public boolean isPaid() {
        return remaining() <= 0.000001;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Pagamento inválido");
        }
        
        if (isPaid()) {
            throw new IllegalStateException("Fatura já quitada");
        }
        
        paidAmount += amount;
        
        if (paidAmount > totalAmount) {
            paidAmount = totalAmount;
        }
    }

    public double installmentValue() {
        return totalAmount / installmentCount;
    }

    @Override
    public String toString() {
        return "Invoice{id=" + id +
                ", student=" + student.getName() +
                ", total=" + String.format(Locale.US, "%.2f", totalAmount) +
                ", paid=" + String.format(Locale.US, "%.2f", paidAmount) +
                ", remaining=" + String.format(Locale.US, "%.2f", remaining()) +
                ", installments=" + installmentCount +
                ", installmentValue=" + String.format(Locale.US, "%.2f", installmentValue()) +
                ", createdAt=" + createdAt +
                ", status=" + (isPaid() ? "PAGA" : "ABERTA") +
                "}";
    }
}