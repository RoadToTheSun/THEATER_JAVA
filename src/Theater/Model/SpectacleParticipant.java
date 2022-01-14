package Theater.Model;

public class SpectacleParticipant {
    private int employeeCode;
    private String spectacleName;
    private String role;

    private Employee employee;
    private Spectacle spectacle;

    public SpectacleParticipant(Employee employee, Spectacle spectacle, String role) {
        this.employee = employee;
        this.spectacle = spectacle;
        this.employeeCode = employee.getCode();
        this.spectacleName = spectacle.getName();
        this.role = role;
    }
}
