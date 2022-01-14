package Theater.Service.TheaterService;

import Theater.Exceptions.*;
import Theater.Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultTheaterService implements TheaterService {

//    private static DefaultTheaterService INSTANCE;
//
//    public static DefaultTheaterService getINSTANCE() {
//        if (INSTANCE == null) INSTANCE = new DefaultTheaterService();
//        return INSTANCE;
//    }

    public DefaultTheaterService(Theater theater) {
        this.theater = theater;
    }

    private final Theater theater;

    @Override
    public List<Ticket> getTicketsForSession(int sessionId, boolean onlyBookings) {
        Session s = findSessionById(sessionId);
        if (s==null) return Collections.emptyList();
        return s.getTickets(onlyBookings);
    }

    private Collective findBy(int direction) throws NoCollectiveFoundException {
        List<Collective> collectives = theater.getCollectives().stream().filter(c->c.getDirection()==direction).collect(Collectors.toList());
        if (collectives.size()==1) return collectives.get(0);
        else throw new NoCollectiveFoundException(String.format("No group with direction = %d", direction));
    }

    @Override
    public List<Employee> getEmployeesOfDirection(int direction) {
        try {
            Collective c = findBy(direction);
            return c.getEmployees();
        } catch (NoCollectiveFoundException e) {
            System.err.println(e.getLocalizedMessage());
        } return Collections.emptyList();
    }

    @Override
    public boolean addEmployeeInDirection(Employee emp) {
        try {
            Collective c = findBy(emp.getDirection());
            return c.addEmployee(emp);
        } catch (NoCollectiveFoundException e) {
            System.err.println(e.getLocalizedMessage());
        } return false;
    }

    @Override
    public boolean addGroup(Collective c) {
        return theater.getCollectives().add(c);
    }

    @Override
    public int addSessionsOnSpectacle(String s, Session ... sessions) {
        Spectacle spec = findSpectacleByName(s);
        return spec == null ? 0 : spec.addSessions(sessions);
    }

    @Override
    public List<Session> getSessionsOnSpectacle(String s) {
        Spectacle spec = findSpectacleByName(s);
        return spec == null ? Collections.emptyList() : spec.getSessions();
    }

    @Override
    public Spectacle findSpectacleByName(String s) {
        List<Spectacle> spectacles = theater.getSpectacles().stream().filter(spec->spec.getName().equalsIgnoreCase(s)).collect(Collectors.toList());
        if (spectacles.size()!=1) {
            try {
                throw new NoSpectaclesFoundException(String.format("Nothing found. Make sure you entered the name correctly: \"%s\"", s));
            } catch (NoSpectaclesFoundException e) {
                System.err.println(e.getLocalizedMessage());
                return null;
            }
        } else return spectacles.get(0);
    }

    @Override
    public List<Session> getAllSessions() {
        List<Session> sessions = new ArrayList<>();
        theater.getSpectacles().forEach(s->sessions.addAll(s.getSessions()));
        return sessions;
    }

    @Override
    public Session  findSessionById(int sessionId) {
        List<Session> sessions = getAllSessions().stream().filter(session -> session.getId()==sessionId).collect(Collectors.toList());
        if (sessions.size()==0) {
            try {
                throw new NoSessionsFoundException(String.format("There is no session with id %s", sessionId));
            } catch (NoSessionsFoundException e) {
                System.err.println(e.getLocalizedMessage());
                return null;
            }
        } else return sessions.get(0);
    }

    @Override
    public Customer findCustomerById(int customerId) {
        List<Customer> customers = theater.getCustomers().stream().filter(customer -> customer.getId()==customerId).collect(Collectors.toList());
        if (customers.size()!=1) {
            try {
                throw new NoCustomersFoundException(String.format("No one found with id %s", customerId));
            } catch (NoCustomersFoundException e) {
                System.err.println(e.getLocalizedMessage());
                return null;
            }
        } else return customers.get(0);
    }

    @Override
    public boolean bookTicketForSession(int sessionId, Customer customer, int spot) {
        Session s = findSessionById(sessionId);
        return s != null && customer.bookTicket(findSessionById(sessionId), spot);
    }

    @Override
    public void addCustomers(Customer ... adding) {
        for (Customer c : adding) {
            if (!theater.getCustomers().contains(c)) {
                theater.getCustomers().add(c);
//                System.out.printf("Покупатель %s добавлен успешно%n", c);
            }
        }
    }
}
