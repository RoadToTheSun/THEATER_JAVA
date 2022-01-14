package Theater.Model;

import java.util.ArrayList;
import java.util.List;

public class Collective {

    private int direction;
    private String info;
    private int employeeCount;

    private List<Employee> employees;

    public Collective(int direction, String info, List<Employee> employees) {
        this.direction = direction;
        this.info = info;
        this.employees = employees;
        this.employeeCount = employees.size();
    }

    public Collective(int direction, String info) {
        this.direction = direction;
        this.info = info;
        this.employees = new ArrayList<>();
        this.employeeCount = 0;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getDirection() {
        return direction;
    }

    public boolean addEmployee(Employee e) {
        return employees.add(e);
    }
}
