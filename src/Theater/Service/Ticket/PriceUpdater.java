package Theater.Service.Ticket;

import Theater.Model.Ticket;

@FunctionalInterface
public interface PriceUpdater {
    Ticket.TicketPrice update(Ticket t, Ticket.TicketPrice newPrice);
}
