package poo;

public class Staff extends Person implements Payable {
    private final String    staffId;
    private String          role;
    private double          monthlySalary;

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