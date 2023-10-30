package carsharing.db;

import carsharing.db.car.Car;
import carsharing.db.car.DbCarDao;
import carsharing.db.company.Company;
import carsharing.db.company.DbCompanyDao;

import java.util.List;
import java.util.Scanner;

public class DbService {
    private final DbCompanyDao dbCompanyDao;
    private final DbCarDao dbCarDao;
    public DbService(DbClient dbClient) {
        this.dbCompanyDao = new DbCompanyDao(dbClient);
        this.dbCarDao = new DbCarDao(dbClient);
    }
    public void addCompany(Scanner sc) {
        System.out.println("\nEnter the company name:");
        dbCompanyDao.add(new Company(sc.nextLine()));
        System.out.println("The company was created!");
    }
    public List<Company> listCompanies() {
        return dbCompanyDao.findAll();
    }
    public Company findById(int id) {
        return dbCompanyDao.findById(id);
    }
    public void addCar(Scanner sc,Company company) {
        System.out.println("\nEnter the car name:");
        dbCarDao.add(new Car(sc.nextLine(), company.getId()));
        System.out.println("The car was added!");
    }
    public List<Car> listCars(Company company) {
        return dbCarDao.findByCompany(company.getId());
    }
}
