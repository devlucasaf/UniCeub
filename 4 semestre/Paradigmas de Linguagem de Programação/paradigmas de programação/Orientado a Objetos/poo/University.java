class University {
    private final String                name;
    private final List<Department>      departments;
    private final Map<UUID, Student>    studentsById;
    private final Map<String, Student>  studentsByCode;
    private final List<Enrollment>      enrollments;
    private final Map<String, Course>   courseIndex;
    private final Map<UUID, Invoice>    invoicesById;
    private final Map<String, Invoice>  invoiceByStudentCode;

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