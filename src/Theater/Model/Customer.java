package Theater.Model;

import Theater.Service.Customer.CustomerService;
import Theater.Service.Customer.DefaultCustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private final CustomerService service = DefaultCustomerService.getINSTANCE();

    private int id;
    private String FIO;
    private String login;
    private int phone;
    private String mail;

    List<Ticket> bookings;

    private Customer(Builder builder) {
        this.id = builder.id;
        this.FIO = builder.FIO;
        this.login = builder.login;
        this.phone = builder.phone;
        this.mail = builder.mail;
        bookings = new ArrayList<>();
    }

    public boolean bookTicket(Session s, int spot) {
        return service.bookTicket(s, this, spot);
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getBookings() {
        return bookings;
    }

    public static class Builder {
        private int id;
        private String FIO;
        private String login;
        private int phone;
        private String mail;

        public Builder(int id) {
            this.id = id;
        }

        private Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder FIO(String FIO) {
            this.FIO = FIO;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder phone(int phone) {
            this.phone = phone;
            return this;
        }

        public Builder mail(String mail) {
            this.mail = mail;
            return this;
        }
        public Customer build() {
            return new Customer(this);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", login=" + login +
                ", phone=" + phone +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && login == customer.login && phone == customer.phone && Objects.equals(FIO, customer.FIO) && Objects.equals(mail, customer.mail);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
