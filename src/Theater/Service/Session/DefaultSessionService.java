package Theater.Service.Session;

import Theater.Exceptions.NoSessionsFoundException;
import Theater.Exceptions.NoTicketsFoundException;
import Theater.Exceptions.TheaterException;
import Theater.Model.Customer;
import Theater.Model.Factory.TicketsFactory;
import Theater.Model.Session;
import Theater.Model.Theater;
import Theater.Model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultSessionService implements SessionService {

    private static DefaultSessionService INSTANCE;

    public static DefaultSessionService getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new DefaultSessionService();
        return INSTANCE;
    }

    private DefaultSessionService() {
    }

    @Override
    public boolean isFilled(Session s) {
        return s.availableTicketsCount()==0;
    }

    @Override
    public void addTickets(Session s, Ticket... adding) {
        for (Ticket t : adding) {
            if (!s.getTickets().contains(t))
                s.getTickets().add(t);
        }
    }

    @Override
    public void addTickets(Session s, int numberToAdd, int step) {
        List<Ticket> tickets = s.getTickets();
        tickets.addAll(tickets.size(), TicketsFactory.generate(s, tickets.size()+1, tickets.size()+numberToAdd, step));
    }

    @Override
    public Ticket findTicket(Session s, Long number) {
        List<Ticket> tickets = s.getTickets().stream().filter(ticket -> ticket.getNumber() == number).collect(Collectors.toList());
        return tickets.size() == 0 ? null : tickets.get(0);
    }

    @Override
    public Ticket book(Session s, Customer c, int spot) throws TheaterException {
        List<Ticket> tickets = s.getTickets();
        if (!isFilled(s)) {
            List<Ticket> temp = tickets.stream().filter(ticket -> ticket.getSpot()==spot).collect(Collectors.toList());
            if (temp.size()>1)
                throw new NoTicketsFoundException(String.format("There are %s tickets with the same spot: %s",temp.size(), spot));
            if (temp.size()==0 || spot>tickets.size())
                throw new NoTicketsFoundException(String.format("Unreachable value for spot: %s", spot));
            Ticket t = temp.remove(0);
            if (!t.isAvailable())
                throw new NoTicketsFoundException(String.format("Spot %d is already booked!", spot));
            else {
                t.book(c);
                Theater.getINSTANCE().service().addCustomers(c);
                return t;
            }
        }
        else {
            throw new NoTicketsFoundException();
        }
    }
}

