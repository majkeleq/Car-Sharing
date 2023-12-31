package carsharing;

import carsharing.db.DbService;
import carsharing.db.car.Car;
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
        System.out.println("\nEnter the customer name:");
        Customer customer = new Customer(sc.nextLine());
        dbService.addCustomer(customer);
        System.out.println("The customer was added!");
    }

    public void chooseCustomer(Scanner sc) {
        List<Customer> customerList = dbService.findAllCustomers();
        int input = dbService.getContext(customerList, "customer", sc);
        while (input != 0) {
            customerMenu(sc, customerList.get(input - 1));
            input = dbService.getContext(customerList, "customer", sc);
        }
    }

    public void rentCar(Scanner sc, Customer customer) {
        List<Company> companyList = dbService.findAllCompanies();
        int input;
        if (!dbService.hasRentedCar(customer)) {
        do {
            input = dbService.getContext(companyList, "company", sc);
            if (input > 0) {
                List<Car> carList = dbService.findAllAvailableCompanyCars(companyList.get(input - 1));
                int input2 = dbService.getContext(carList, "car", sc);
                if (input2 > 0) {
                    System.out.println(dbService.rentCar(customer, carList.get(input - 1)));
                    input = 0;
                }
            }
        } while (input != 0); }
        else {
            System.out.println("\nYou've already rented a car!");
        }

    }


    public void customerMenu(Scanner sc, Customer customer) {
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("\n1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1 -> rentCar(sc, customer);
                case 2 -> System.out.println(dbService.returnCar(customer));
                case 3 -> System.out.println(dbService.showRentedCar(customer));
                case 0 -> toContinue = false;

            }
        }
    }
}
