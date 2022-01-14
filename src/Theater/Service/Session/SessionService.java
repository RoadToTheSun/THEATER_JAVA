package Theater.Service.Session;

import Theater.Exceptions.TheaterException;
import Theater.Model.Customer;
import Theater.Model.Session;
import Theater.Model.Ticket;

import java.util.List;

public interface SessionService {
    boolean isFilled(Session s);
    void addTickets(Session s, Ticket ... t);
    void addTickets(Session s, int numberToAdd, int step);
    Ticket findTicket(Session s, Long number);
    Ticket book(Session s, Customer c, int spot) throws TheaterException;
}
