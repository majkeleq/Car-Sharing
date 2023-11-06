package carsharing.db.customer;

import java.util.List;

public interface CustomerDao {
    void add(Customer customer);
    Customer findCustomer(int id);
    List<Customer> findAll();
}
