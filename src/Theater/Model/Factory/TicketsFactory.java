package Theater.Model.Factory;

import Theater.Model.Session;
import Theater.Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsFactory {
    public static List<Ticket> generate(Session s, int from, int to, int step) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = from; i <= to; i+=step) {
            tickets.add(new Ticket.Builder(s).spot(i).number(Long.parseLong(String.valueOf(s.getId()).concat(String.valueOf(i)))).build());
        }
        return tickets;
    }
}
