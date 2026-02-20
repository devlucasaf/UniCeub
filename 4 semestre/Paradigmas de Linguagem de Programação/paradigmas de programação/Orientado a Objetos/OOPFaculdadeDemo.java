import java.time.LocalDate;
import java.util.*;

public class OOPFaculdadeDemo {
    public static void main(String[] args) {
        University uni = new University("Universidade Exemplo");

        Department comp = new Department("Computação");
        Department math = new Department("Matemática");
        uni.addDepartment(comp);
        uni.addDepartment(math);

        Professor profAna = new Professor("Ana", "ana@uni.edu", "P001", 120.0);
        Professor profBruno = new Professor("Bruno", "bruno@uni.edu", "P002", 150.0);
        Staff staffCarla = new Staff("Carla", "carla@uni.edu", "S001", "Secretaria");

        comp.addMember(profAna);
        comp.addMember(staffCarla);
        math.addMember(profBruno);

        Course poo = new Course("POO-101", "Programação Orientada a Objetos", 60);
        Course calc = new Course("MAT-110", "Cálculo I", 60);
        Course alg = new Course("MAT-120", "Álgebra Linear", 60);

        comp.addCourse(poo);
        math.addCourse(calc);
        math.addCourse(alg);

        poo.assignProfessor(profAna);
        calc.assignProfessor(profBruno);
        alg.assignProfessor(profBruno);

        Student lucas = new Student("Lucas", "lucas@uni.edu", "A1001");
        Student maria = new Student("Maria", "maria@uni.edu", "A1002");
        Student joao = new Student("João", "joao@uni.edu", "A1003");

        uni.registerStudent(lucas);
        uni.registerStudent(maria);
        uni.registerStudent(joao);

        Enrollment e1 = uni.enroll(lucas, poo);
        Enrollment e2 = uni.enroll(lucas, calc);
        Enrollment e3 = uni.enroll(maria, poo);
        Enrollment e4 = uni.enroll(maria, alg);
        Enrollment e5 = uni.enroll(joao, calc);

        e1.addAssessment(new Assessment("Trabalho 1", 10.0));
        e1.addAssessment(new Assessment("Prova 1", 10.0));
        e2.addAssessment(new Assessment("Lista 1", 10.0));
        e2.addAssessment(new Assessment("Prova 1", 10.0));
        e3.addAssessment(new Assessment("Trabalho 1", 10.0));
        e3.addAssessment(new Assessment("Prova 1", 10.0));
        e4.addAssessment(new Assessment("Prova 1", 10.0));
        e5.addAssessment(new Assessment("Lista 1", 10.0));
        e5.addAssessment(new Assessment("Prova 1", 10.0));

        profAna.grade(e1, "Trabalho 1", 8.5);
        profAna.grade(e1, "Prova 1", 7.0);
        profBruno.grade(e2, "Lista 1", 9.0);
        profBruno.grade(e2, "Prova 1", 6.5);

        profAna.grade(e3, "Trabalho 1", 10.0);
        profAna.grade(e3, "Prova 1", 9.5);

        profBruno.grade(e4, "Prova 1", 7.5);

        profBruno.grade(e5, "Lista 1", 5.0);
        profBruno.grade(e5, "Prova 1", 4.0);

        uni.createInvoice(lucas, 2, 350.0);
        uni.createInvoice(maria, 2, 350.0);
        uni.createInvoice(joao, 1, 175.0);

        lucas.payInvoice(uni.getInvoiceFor(lucas).getId(), 700.0);
        maria.payInvoice(uni.getInvoiceFor(maria).getId(), 350.0);

        System.out.println("==== Pessoas (Polimorfismo via Person) ====");
        
        List<Person> people = new ArrayList<>();
        
        people.add(profAna);
        people.add(profBruno);
        people.add(staffCarla);
        people.add(lucas);
        people.add(maria);
        people.add(joao);

        for (Person p : people) {
            System.out.println(p.summary());
        }

        System.out.println();
        System.out.println("==== Histórico e Médias ====");
        printTranscript(lucas);
        printTranscript(maria);
        printTranscript(joao);

        System.out.println();
        System.out.println("==== Financeiro ====");
        System.out.println(uni.getInvoiceFor(lucas));
        System.out.println(uni.getInvoiceFor(maria));
        System.out.println(uni.getInvoiceFor(joao));

        System.out.println();
        System.out.println("==== Relatório de Cursos ====");
        
        for (Department d : uni.getDepartments()) {
            System.out.println("Departamento: " + d.getName());
            
            for (Course c : d.getCourses()) {
                System.out.println(" - " + c.details());
            }
        }

        System.out.println();
        System.out.println("==== Folha de Pagamento (Interface Payable) ====");
        
        List<Payable> payroll = new ArrayList<>();
        
        payroll.add(profAna);
        payroll.add(profBruno);
        payroll.add(staffCarla);
        
        for (Payable pay : payroll) {
            System.out.println(pay.paymentSummary());
        }
    }

    private static void printTranscript(Student s) {
        System.out.println("Aluno: " + s.getName() + " (" + s.getStudentId() + ")");
        
        for (Enrollment e : s.getEnrollments()) {
            System.out.println("  Curso: " + e.getCourse().getCode() + " - " + e.getCourse().getName());
            System.out.println("  Professor: " + (e.getCourse().getProfessor() == null ? "N/A" : e.getCourse().getProfessor().getName()));
            System.out.println("  Nota final: " + String.format(Locale.US, "%.2f", e.finalScore()));
            System.out.println("  Situação: " + e.status());
        }
    }
}

abstract class Person {
    private final UUID id;
    private String name;
    private String email;
    private LocalDate createdAt;

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

interface Payable {
    double paymentAmount();
    String paymentSummary();
}

class Staff extends Person implements Payable {
    private final String staffId;
    private String role;
    private double monthlySalary;

    public Staff(String name, String email, String staffId, String role) {
        super(name, email);
        
        setRole(role);
        
        this.staffId = Objects.requireNonNull(staffId);
        this.monthlySalary = 3200.0;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Cargo inválido");
        }
        
        this.role = role.trim();
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary <= 0) {
            throw new IllegalArgumentException("Salário inválido");
        }
        
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double paymentAmount() {
        return monthlySalary;
    }

    @Override
    public String paymentSummary() {
        return "Staff " + getName() + " (" + staffId + ") recebe R$ " + String.format(Locale.US, "%.2f", paymentAmount());
    }

    @Override
    public String summary() {
        return super.summary() + " | staffId=" + staffId + " | role=" + role;
    }
}

class Professor extends Person implements Payable {
    private final String professorId;
    private double hourlyRate;
    private int hoursThisMonth;

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

class Student extends Person {
    private final String studentId;
    private final List<Enrollment> enrollments;

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

class Course {
    private final String code;
    private String name;
    private int workloadHours;
    private Professor professor;

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

class Enrollment {
    private final UUID id;
    private final Student student;
    private final Course course;
    private final Map<String, Assessment> assessments;

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

class Assessment {
    private final String name;
    private final double maxScore;
    private Double score;

    public Assessment(String name, double maxScore) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da avaliação inválido");
        }
        
        if (maxScore <= 0) {
            throw new IllegalArgumentException("Nota máxima inválida");
        }
        
        this.name = name.trim();
        this.maxScore = maxScore;
        this.score = null;
    }

    public String getName() {
        return name;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score < 0 || score > maxScore) {
            throw new IllegalArgumentException("Nota fora do intervalo");
        }
        
        this.score = score;
    }

    public double normalizedScore() {
        if (score == null) {
            return 0.0;
        }
        
        return (score / maxScore) * 10.0;
    }

    @Override
    public String toString() {
        return "Assessment{" + "name='" + name + '\'' + ", maxScore=" + maxScore + ", score=" + score + '}';
    }
}

class Department {
    private final String name;
    private final List<Course> courses;
    private final List<Person> members;

    public Department(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do departamento inválido");
        }
        
        this.name = name.trim();
        this.courses = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course c) {
        courses.add(Objects.requireNonNull(c));
    }

    public void addMember(Person p) {
        members.add(Objects.requireNonNull(p));
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public List<Person> getMembers() {
        return Collections.unmodifiableList(members);
    }
}

class Invoice {
    private final UUID id;
    private final Student student;
    private final int installmentCount;
    private final double totalAmount;
    private double paidAmount;
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

class University {
    private final String name;
    private final List<Department> departments;
    private final Map<UUID, Student> studentsById;
    private final Map<String, Student> studentsByCode;
    private final List<Enrollment> enrollments;
    private final Map<String, Course> courseIndex;
    private final Map<UUID, Invoice> invoicesById;
    private final Map<String, Invoice> invoiceByStudentCode;

    public University(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da universidade inválido");
        }
        
        this.name = name.trim();
        this.departments = new ArrayList<>();
        this.studentsById = new HashMap<>();
        this.studentsByCode = new HashMap<>();
        this.enrollments = new ArrayList<>();
        this.courseIndex = new HashMap<>();
        this.invoicesById = new HashMap<>();
        this.invoiceByStudentCode = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addDepartment(Department d) {
        departments.add(Objects.requireNonNull(d));
        
        for (Course c : d.getCourses()) {
            courseIndex.put(c.getCode(), c);
        }
    }

    public List<Department> getDepartments() {
        return Collections.unmodifiableList(departments);
    }

    public void registerStudent(Student s) {
        Objects.requireNonNull(s);
        studentsById.put(s.getId(), s);
        studentsByCode.put(s.getStudentId(), s);
    }

    public Student findStudentByCode(String code) {
        return studentsByCode.get(code);
    }

    public Enrollment enroll(Student s, Course c) {
        Objects.requireNonNull(s);
        Objects.requireNonNull(c);

        Enrollment e = new Enrollment(s, c);

        s.addEnrollment(e);

        enrollments.add(e);

        return e;
    }

    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public Invoice createInvoice(Student s, int installments, double total) {
        Objects.requireNonNull(s);

        Invoice inv = new Invoice(s, installments, total);

        invoicesById.put(inv.getId(), inv);
        invoiceByStudentCode.put(s.getStudentId(), inv);

        return inv;
    }

    public Invoice getInvoiceFor(Student s) {
        return invoiceByStudentCode.get(s.getStudentId());
    }

    public void payInvoice(UUID invoiceId, double amount) {
        Invoice inv = invoicesById.get(invoiceId);
        if (inv == null) {
            throw new IllegalArgumentException("Invoice não encontrada");
        }

        inv.pay(amount);
    }
}
