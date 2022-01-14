package Theater.Service.Ticket;

import Theater.Model.Ticket;

import java.time.LocalDate;
import java.util.List;

public class PriceUpdaterImpl {

    private static boolean illegalPrice(List<Ticket.TicketPrice> prices, LocalDate date_from, LocalDate date_to) throws IllegalArgumentException {
        if (date_from.isAfter(date_to))
            throw new IllegalArgumentException("Price .date_from is After .date_to");
        boolean alreadyExists = prices.stream().
                anyMatch(p -> /*p.date_from.equals(price.date_from)  || */ p.getDateTo().equals(date_to));
        boolean illegalState = prices.stream().
                anyMatch(p -> date_from.isBefore(p.getDateTo()));
        if (alreadyExists || illegalState)
            throw new IllegalArgumentException(alreadyExists ? "Price already exists" : "Incorrect date of price");
        return false;
    }

    public static Ticket.TicketPrice update(Ticket t, Ticket.TicketPrice newPrice) {
        Ticket.TicketPrice oldP = t.getCurrentPrice();
        List<Ticket.TicketPrice> prices = t.getPrices();
        if (prices.size() > 0) {
            try {
                oldP = prices.get(prices.size() - 1);
                LocalDate oldDateTo = oldP.getDateTo();
                oldP.setDateTo(newPrice.getDateFrom().minusDays(1));
                if (!illegalPrice(prices, newPrice.getDateFrom(), newPrice.getDateTo())) {
                    prices.add(newPrice);
                } else {
                    oldP.setDateTo(oldDateTo);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getLocalizedMessage());
            }
        } else {
            System.out.println("Could not be possible");
        }
        return oldP;
    }
}
