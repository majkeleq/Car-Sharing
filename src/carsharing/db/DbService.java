package carsharing.db;

import carsharing.db.car.Car;
import carsharing.db.car.DbCarDao;
import carsharing.db.company.Company;
import carsharing.db.company.DbCompanyDao;
import carsharing.db.customer.Customer;
import carsharing.db.customer.DbCustomerDao;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DbService {
    private final DbCompanyDao dbCompanyDao;
    private final DbCarDao dbCarDao;
    private final DbCustomerDao dbCustomerDao;

    public DbService(DbClient dbClient) {
        this.dbCompanyDao = new DbCompanyDao(dbClient);
        this.dbCarDao = new DbCarDao(dbClient);
        this.dbCustomerDao = new DbCustomerDao(dbClient);
    }

    public void addCompany(Scanner sc) {
        System.out.println("\nEnter the company name:");
        dbCompanyDao.add(new Company(sc.nextLine()));
        System.out.println("The company was created!");
    }

    public List<Company> findAllCompanies() {
        return dbCompanyDao.findAll();
    }

    public Company findCompanyById(int id) {
        return dbCompanyDao.findById(id);
    }

    public void addCar(Scanner sc, Company company) {
        System.out.println("\nEnter the car name:");
        dbCarDao.add(new Car(sc.nextLine(), company.getId()));
        System.out.println("The car was added!");
    }

    public List<Car> findAllCompanyCars(Company company) {
        return dbCarDao.findByCompany(company.getId());
    }
    public List<Car> findAllAvailableCompanyCars(Company company) {
        return dbCarDao.findAvailableCarsByCompany(company.getId());
    }

    public void addCustomer(Customer customer) {
        dbCustomerDao.add(customer);
    }

    public List<Customer> findAllCustomers() {
        return dbCustomerDao.findAll();
    }

    public int getContext(List<?> list, String listName, Scanner sc) {
        int input = 0;
        if (list.isEmpty()) {
            System.out.printf("\nThe %s list is empty!\n", listName);
        } else {
            StringBuilder result = new StringBuilder();
            do {
                result.setLength(0);
                result.append(String.format("\nChoose %s:\n", listName));
                AtomicInteger index = new AtomicInteger(1);
                list.forEach(o -> result.append(index.getAndIncrement()).append(". ").append(o.toString()).append("\n"));
                result.append("0. Back");
                System.out.println(result);
                input = Integer.parseInt(sc.nextLine());
            } while (input < 0 || input > list.size());
        }
        return input;
    }
    public String rentCar(Customer customer, Car car) {
        dbCustomerDao.rentCar(customer,car);
        dbCarDao.rentCar(car);
        return String.format("\nYou rented '%s'", car.getName());
    }

    public String returnCar(Customer customer) {
        String result = dbCustomerDao.returnCar(customer);
        if (result.equals("null")) {
            return "\nYou didn't rent a car!";
        }
        dbCarDao.returnCar(Integer.parseInt(result));
        return "\nYou've returned a rented car!";
    }
    public String showRentedCar(Customer customer) {
        String carId = dbCustomerDao.getCarId(customer);
        System.out.println(carId);
        if (carId.equals("null")) {
            return "\nYou didn't rent a car!";
        }
        Car car = dbCarDao.getCarById(Integer.parseInt(carId));
        return  "\nYour rented car:\n" +
                car.getName() +
                "\nCompany:\n" +
                dbCompanyDao.findByCar(car).getName();
    }
}
