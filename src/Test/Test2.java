package Test;

import Theater.Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<Customer> list1 = new ArrayList<>(List.of(new Customer.Builder(1).build(), new Customer.Builder(2).build()));
        List<Customer> list2 = list1;
        list1.add(new Customer.Builder(3).build());
        System.out.println(list1);
        System.out.println(list2);
    }
}
