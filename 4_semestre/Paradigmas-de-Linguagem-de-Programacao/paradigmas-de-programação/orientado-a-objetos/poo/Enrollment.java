package poo;

public class Enrollment {
    private final UUID                      id;
    private final Student                   student;
    private final Course                    course;
    private final Map<String, Assessment>   assessments;

    public Enrollment(Student student, Course course) {
        this.id = UUID.randomUUID();
        this.student = Objects.requireNonNull(student);
        this.course = Objects.requireNonNull(course);
        this.assessments = new LinkedHashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void addAssessment(Assessment a) {
        if (a == null) {
            throw new IllegalArgumentException("Avaliação nula");
        }
        
        if (assessments.containsKey(a.getName())) {
            throw new IllegalArgumentException("Avaliação duplicada");
        }
        
        assessments.put(a.getName(), a);
    }

    public void setScore(String assessmentName, double score) {
        Assessment a = assessments.get(assessmentName);
        
        if (a == null) {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
        
        a.setScore(score);
    }

    public double finalScore() {
        if (assessments.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        
        for (Assessment a : assessments.values()) {
            sum += a.normalizedScore();
        }
        
        return sum / assessments.size();
    }

    public String status() {
        double score = finalScore();
        
        if (assessments.isEmpty()) {
            return "Sem notas";
        }
        
        return score >= 6.0 ? "Aprovado" : "Reprovado";
    }

    @Override
    public String toString() {
        return "Enrollment{" + "id=" + id + ", student=" + student.getName() + ", course=" + course.getCode() + ", final=" + finalScore() + '}';
    }
}