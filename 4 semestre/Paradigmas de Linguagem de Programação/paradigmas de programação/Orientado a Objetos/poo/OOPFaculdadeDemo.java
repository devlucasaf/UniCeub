import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
