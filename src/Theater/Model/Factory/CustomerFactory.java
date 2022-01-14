package Theater.Model.Factory;

import Theater.Model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerFactory {
    public static List<Customer> generate(int fromId, int toId) {
        List<Customer> customers = new ArrayList<>();
        Random rnd = new Random();
        for (int id = fromId; id <= toId; id++) {
            customers.add(new Customer.Builder(id).build());
        }
        return customers;
    }
}
