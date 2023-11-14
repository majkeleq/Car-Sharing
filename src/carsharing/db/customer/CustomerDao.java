package carsharing.db.customer;

import carsharing.db.car.Car;

import java.util.List;

public interface CustomerDao {
    void add(Customer customer);
    Customer findCustomer(int id);
    List<Customer> findAll();
    void rentCar(Customer customer, Car car);
    Integer returnCar(Customer customer);
    Integer getCarId(Customer customer);
}
