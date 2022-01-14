package Theater.Service.Customer;

import Theater.Model.Customer;
import Theater.Model.Session;

public interface CustomerService {
    boolean bookTicket(Session s, Customer customer, int spot);
}
