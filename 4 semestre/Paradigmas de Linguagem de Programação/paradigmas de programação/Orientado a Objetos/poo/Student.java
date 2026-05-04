package poo;

public class Student extends Person {
    private final String            studentId;
    private final List<Enrollment>  enrollments;

    public Student(String name, String email, String studentId) {
        super(name, email);
        this.studentId = Objects.requireNonNull(studentId);
        this.enrollments = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    void addEnrollment(Enrollment e) {
        enrollments.add(Objects.requireNonNull(e));
    }

    public double gpaLikeAverage() {
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        
        for (Enrollment e : enrollments) {
            sum += e.finalScore();
        }
        
        return sum / enrollments.size();
    }

    public void payInvoice(UUID invoiceId, double amount) {
        if (invoiceId == null) {
            throw new IllegalArgumentException("Invoice inválida");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Pagamento inválido");
        }
    }

    @Override
    public String summary() {
        return super.summary() + " | studentId=" + studentId + " | cursos=" + enrollments.size() + " | media=" + String.format(Locale.US, "%.2f", gpaLikeAverage());
    }
}