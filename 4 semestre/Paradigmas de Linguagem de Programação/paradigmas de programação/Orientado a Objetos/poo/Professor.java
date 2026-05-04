package poo;

public class Professor extends Person implements Payable {
    private final String    professorId;
    private double          hourlyRate;
    private int             hoursThisMonth;

    public Professor(String name, String email, String professorId, double hourlyRate) {
        super(name, email);
        
        setHourlyRate(hourlyRate);
        
        this.professorId = Objects.requireNonNull(professorId);
        this.hoursThisMonth = 40;
    }

    public String getProfessorId() {
        return professorId;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate <= 0) {
            throw new IllegalArgumentException("Valor hora inválido");
        }
        
        this.hourlyRate = hourlyRate;
    }

    public int getHoursThisMonth() {
        return hoursThisMonth;
    }

    public void setHoursThisMonth(int hoursThisMonth) {
        if (hoursThisMonth < 0) {
            throw new IllegalArgumentException("Horas inválidas");
        }
        
        this.hoursThisMonth = hoursThisMonth;
    }

    public void grade(Enrollment enrollment, String assessmentName, double score) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Matrícula nula");
        }
        
        if (enrollment.getCourse().getProfessor() != this) {
            throw new IllegalStateException("Professor não associado ao curso");
        }
        
        enrollment.setScore(assessmentName, score);
    }

    @Override
    public double paymentAmount() {
        return hourlyRate * hoursThisMonth;
    }

    @Override
    public String paymentSummary() {
        return "Professor " + getName() + " (" + professorId + ") recebe R$ " + String.format(Locale.US, "%.2f", paymentAmount());
    }

    @Override
    public String summary() {
        return super.summary() + " | professorId=" + professorId + " | hourlyRate=" + hourlyRate;
    }
}