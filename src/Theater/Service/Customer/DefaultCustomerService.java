package Theater.Service.Customer;

import Theater.Exceptions.TheaterException;
import Theater.Model.Customer;
import Theater.Model.Session;
import Theater.Model.Ticket;

public class DefaultCustomerService implements CustomerService {

    private DefaultCustomerService() {
    }

    private static DefaultCustomerService INSTANCE;

    public static DefaultCustomerService getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new DefaultCustomerService();
        return INSTANCE;
    }

    @Override
    public boolean bookTicket(Session s, Customer customer, int spot) {
        try {
            Ticket t = s.book(customer, spot);
            customer.getBookings().add(t);
//            System.out.printf("Успешный заказ билета: %s%n", t);
            return true;
        } catch (TheaterException e) {
            e.printStackTrace();
        }
        return false;
    }
}
