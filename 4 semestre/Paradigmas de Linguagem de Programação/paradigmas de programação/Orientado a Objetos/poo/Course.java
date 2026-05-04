package poo;

public class Course {
    private final String    code;
    private String          name;
    private int             workloadHours;
    private Professor       professor;

    public Course(String code, String name, int workloadHours) {
        this.code = Objects.requireNonNull(code).trim();
        
        setName(name);
        setWorkloadHours(workloadHours);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do curso inválido");
        }
        
        this.name = name.trim();
    }

    public int getWorkloadHours() {
        return workloadHours;
    }

    public void setWorkloadHours(int workloadHours) {
        if (workloadHours <= 0) {
            throw new IllegalArgumentException("Carga horária inválida");
        }
        
        this.workloadHours = workloadHours;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void assignProfessor(Professor professor) {
        this.professor = Objects.requireNonNull(professor);
    }

    public String details() {
        String prof = professor == null ? "Sem professor" : professor.getName();
        return code + " | " + name + " | CH=" + workloadHours + "h | Prof=" + prof;
    }

    @Override
    public String toString() {
        return details();
    }
}