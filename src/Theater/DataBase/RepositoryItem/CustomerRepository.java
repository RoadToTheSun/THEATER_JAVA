package Theater.DataBase.RepositoryItem;

import Theater.DataBase.RepositoryImpl;
import Theater.Exceptions.NoCustomersFoundException;
import Theater.Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerRepository extends RepositoryImpl<Integer, Customer> {

    private CustomerRepository() {}

    private static CustomerRepository INSTANCE;

    public static CustomerRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new CustomerRepository();
        return INSTANCE;
    }

    @Override
    public List<Customer> findAll() {
        return executeSelect("SELECT * FROM CUSTOMER");
    }

    @Override
    public Customer findBy(Integer id) {
        List<Customer> customers = executeSelect(String.format("SELECT * FROM CUSTOMER WHERE id = %s", id));
        return customers.size() > 0 ? customers.get(0) : null;
    }

    @Override
    public int delete(Customer value) {
        return 0;
    }

    @Override
    protected List<Customer> executeSelect(String sql) /*throws NoCustomersFoundException*/ {
        List<Customer> customers = new ArrayList<>();
        try (
                Connection connection = super.connection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //TODO Session s = (Session) rs.getRef("session_id");
                //TODO Customer c = (Customer) rs.getRef("customer_id");
                Customer c = new Customer
                        .Builder(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .FIO(rs.getString("fio"))
                        .mail(rs.getString("mail"))
                        .phone(rs.getInt("phone"))
                        .build();
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
