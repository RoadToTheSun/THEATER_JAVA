package Theater.Model;

import Theater.Model.Factory.PriceGenerator;
import Theater.Service.Ticket.PriceUpdater;
import Theater.Service.Ticket.PriceUpdaterImpl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ticket {

    private static final PriceUpdater updater = PriceUpdaterImpl::update;

    private final Long number;
    private final int sessionId;
    private final int spot;

    private int customerID;

    private LinkedList<TicketPrice> prices = new LinkedList<>(); // list of prices in different amounts of time
    private final Session session;
    private Customer customer;

    private Ticket(Builder builder) {
        spot = builder.spot;
        customer = builder.customer;
        customerID = builder.customerID;
        session = builder.session;
        sessionId = session.getId();
//        number = Long.parseLong(String.valueOf(session_id).concat(String.valueOf(spot)));
        number = builder.number;
        prices.add(new TicketPrice(this, LocalDate.now().plusYears(1)));
        prices.get(0).setDateFrom(LocalDate.of(1,1,1));
    }

    public void book(Customer c) {
//        this.session = s;
//        this.session_id = s.getId();
        this.customer = c;
        this.customerID = c.getId();
    }

    public boolean isAvailable() {
        return customer==null;
    }

    public TicketPrice getCurrentPrice() {
        return prices.stream().filter(price ->
               (price.dateFrom.isBefore(LocalDate.now()) || price.dateFrom.isEqual(LocalDate.now())) &&
               (price.dateTo.isAfter(LocalDate.now()) || price.dateTo.isEqual(LocalDate.now())) )
               .collect(Collectors.toList()).get(0);
    }


    /**
     * @param newPrice price to change
     * @return old price in normal behavior :else updated price
     */
    public TicketPrice updatePrice(TicketPrice newPrice) {
        return updater.update(this, newPrice);
    }

    public LinkedList<TicketPrice> getPrices() {
        return prices;
    }

    public int getSpot() {
        return spot;
    }

    public long getNumber() {
        return number;
    }

    public int getSessionId() {
        return sessionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public static class Builder {
        private int spot;
        private Long number;
        private final Session session;
        private Customer customer;
        private int customerID = -1;

        public Builder(Session session) {
            this.session = session;
        }
        public Builder number(long number) {
            this.number = number;
            return this;
        }
        public Builder spot(int spot) {
            this.spot = spot;
            return this;
        }
        public Builder customer(Customer customer) {
            this.customer = customer;
            this.customerID = customer!=null ? customer.getId() : customerID;
            return this;
        }

//        public Builder customerID(int id) {
//            this.customer = null;
//            this.customerID = id;
//            return this;
//        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", session_id=" + sessionId +
                ", spot=" + spot +
                ", customerID=" + customerID +
                ", curPrice=" + getCurrentPrice().getPrice() +
                ", prices=" + prices +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(number, ticket.number) && sessionId == ticket.sessionId && spot == ticket.spot && customerID == ticket.customerID;
    }

//    @Override
//    public int hashCode() {
//        return number;
//    }

    public static class TicketPrice {
        protected static int sequence = 0;

        protected int id;
        protected int price;
        protected LocalDate dateFrom;
        protected LocalDate dateTo;

        private final Long ticketNumber;
        private final Ticket ticket;

        private TicketPrice(Ticket ticket, LocalDate dateTo) {
            this.id = sequence++;
            this.price = PriceGenerator.get(ticket.spot);
            this.dateFrom = LocalDate.now();
            this.dateTo = dateTo;
            this.ticket = ticket;
            this.ticketNumber = ticket.getNumber();
        }

        public TicketPrice(int price, Ticket ticket, LocalDate dateTo) {
            this(ticket, dateTo);
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Long getTicketNumber() {
            return ticketNumber;
        }

        public int getPrice() {
            return price;
        }

        public Ticket getTicket() {
            return ticket;
        }

        public LocalDate getDateFrom() {
            return dateFrom;
        }

        public LocalDate getDateTo() {
            return dateTo;
        }

        public void setDateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
        }

        public void setDateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
        }

        public TicketPrice setPrice(int price) {
            this.price = price;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TicketPrice{");
            sb.append("id=").append(id);
            sb.append(", price=").append(price);
            sb.append(", date_from=").append(dateFrom);
            sb.append(", date_to=").append(dateTo);
            sb.append('}');
            return sb.toString();
        }
    }
}

