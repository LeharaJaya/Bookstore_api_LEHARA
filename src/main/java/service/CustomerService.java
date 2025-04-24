package service;

import model.Customer;
import exception.CustomerNotFoundException;

import java.util.*;

public class CustomerService {
    private static final Map<Integer, Customer> customers = new HashMap<>();
    private static int idCounter = 1;

    private static final CustomerService instance = new CustomerService();
    public static CustomerService getInstance() {
        return instance;
    }

    private CustomerService() {}

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomer(int id) {
        Customer customer = customers.get(id);
        if (customer == null) throw new CustomerNotFoundException(id);
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        customer.setId(idCounter++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer updateCustomer(int id, Customer updated) {
        if (!customers.containsKey(id)) throw new CustomerNotFoundException(id);
        updated.setId(id);
        customers.put(id, updated);
        return updated;
    }

    public void deleteCustomer(int id) {
        if (!customers.containsKey(id)) throw new CustomerNotFoundException(id);
        customers.remove(id);
    }
}
