package Theater.Service.TheaterService;

import Theater.Exceptions.NoSessionsFoundException;
import Theater.Model.*;

import java.util.List;

public interface TheaterService {
    List<Ticket> getTicketsForSession(int sessionId, boolean onlyBookings);

    void addCustomers(Customer ... adding);
    Customer findCustomerById(int customerId);
//    List<Customer> getAllCustomers();
    boolean bookTicketForSession(int sessionId, Customer customer, int spot);

    List<Employee> getEmployeesOfDirection(int direction);
    boolean addEmployeeInDirection(Employee e);

    boolean addGroup(Collective c);

    List<Session> getSessionsOnSpectacle(String name);
    List<Session> getAllSessions();
    Session findSessionById(int sessionId);

    int addSessionsOnSpectacle(String name, Session ... sessions);
    Spectacle findSpectacleByName(String name);
}


