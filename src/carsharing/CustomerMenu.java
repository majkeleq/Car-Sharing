package carsharing;

import carsharing.db.DbService;
import carsharing.db.company.Company;
import carsharing.db.customer.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private final DbService dbService;
    public CustomerMenu(DbService dbService) {
        this.dbService = dbService;
    }

    public void addCustomer(Scanner sc) {
        System.out.println("Enter the customer name:");
        Customer customer = new Customer(sc.nextLine());
        dbService.addCustomer(customer);
        System.out.println("The customer was added!");
    }
    public void chooseCustomer(Scanner sc) {
        List<Customer> customerList = dbService.findAllCustomers();
        int input = dbService.getContext(customerList, "customer", sc);

    }
}
