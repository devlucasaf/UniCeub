package poo;

public class Department {
    private final String        name;
    private final List<Course>  courses;
    private final List<Person>  members;

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