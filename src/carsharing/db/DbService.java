package carsharing.db;

import carsharing.db.car.Car;
import carsharing.db.car.DbCarDao;
import carsharing.db.company.Company;
import carsharing.db.company.DbCompanyDao;
import carsharing.db.customer.Customer;
import carsharing.db.customer.DbCustomerDao;

import java.util.List;
import java.util.Scanner;

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

    public void addCustomer(Customer customer) {
        dbCustomerDao.add(customer);
    }

    public List<Customer> findAllCustomers() {
        return dbCustomerDao.findAll();
    }

    public int getContext(List<?> list, String listName, Scanner sc) {
        int input = 0;
        if (list.isEmpty()) {
            System.out.printf("The %s list is empty!\n", listName);
        } else {
            StringBuilder result = new StringBuilder();
            while (true) {
                result.setLength(0);
                result.append(String.format("Choose %s:\n", listName));
                list.forEach(o -> result.append(o.toString()).append("\n"));
                result.append("0.Back");
                System.out.println(result);
                input = Integer.parseInt(sc.nextLine());
                if (input >= 0 && input <= list.size()) {
                    break;
                }
            }
        }
        return input;
    }
}
