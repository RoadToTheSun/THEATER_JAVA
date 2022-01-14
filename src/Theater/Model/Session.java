package Theater.Model;

import Theater.Exceptions.TheaterException;
import Theater.Service.Session.DefaultSessionService;
import Theater.Service.Session.SessionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Session {

    private final SessionService service = DefaultSessionService.getINSTANCE();

    private int id;
    private LocalDate date;
    private LocalTime time;

    private Spectacle spectacle;
    private String spectacleName;
    private List<Ticket> tickets;

    private Session(Builder builder) {
        this.spectacle = builder.spectacle != null ? builder.spectacle : null;
        this.spectacleName = spectacle != null ? spectacle.getName() : null;
        this.date = builder.date;
        this.time = builder.time;
        this.id = builder.id;
//        this.id = Integer.parseInt(String.valueOf(month).concat(String.valueOf(day)).concat(String.valueOf(time.toSecondOfDay())));
        //TODO
        this.tickets = builder.tickets;
//        tickets = TicketsFactory.generate(this, 1, Theater.SPOTS_NUMBER);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Ticket> getTickets(boolean onlyBookings) {
        return tickets.stream().filter(ticket -> onlyBookings != ticket.isAvailable()).collect(Collectors.toList());
    }

    public boolean isFilled() {
        return service.isFilled(this);
    }

    public void addTickets(Ticket ... adding) {
        service.addTickets(this, adding);
    }

    public void addTickets(int numberToAdd, int step) {
        service.addTickets(this, numberToAdd, step);
    }

    public Ticket findTicketById(Long number) {
        return service.findTicket(this, number);
    }

    public String getSpectacleName() {
        return spectacleName;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public LocalTime getTime() {
        return time;
    }

    public int availableTicketsCount() {return getTickets(false).size();}

    public void link_spectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
        this.spectacleName = spectacle.getName();
    }

    public Ticket book(Customer c, int spot) throws TheaterException {
        return service.book(this, c, spot);
    }

    public static class Builder {
        private int id;
        private LocalDate date;
        private LocalTime time;

        private Spectacle spectacle;
        private String spectacleName;
        private List<Ticket> tickets = new ArrayList<>();

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder spectacle(Spectacle spectacle) {
            this.spectacle = spectacle;
            return this;
        }

        public Builder tickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\n").append(String.format("%-10s", "Сеанс №")).append(String.format("%30s", "Спектакль")).append(String.format("%13s", "Дата")).append(String.format("%10s", "Время")).append(String.format("%18s", "Кол.дост.билетов")).append("\n")
                .append(String.format("%-10s", id)).append(String.format("%30s", spectacleName)).append(String.format("%13s", date)).append(String.format("%10s", time)).append(String.format("%18d", availableTicketsCount()))
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Session) {
            Session s = (Session) obj;
            return id == s.id && date.equals(s.date) && time.equals(s.time);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
