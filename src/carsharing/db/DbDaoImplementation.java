package carsharing.db;

import java.util.List;
import java.util.Scanner;

public class DbDaoImplementation {
    private final DbCompanyDao dbCompanyDao;
    public DbDaoImplementation(DbClient dbClient) {
        this.dbCompanyDao = new DbCompanyDao(dbClient);
    }
    public void addCompany(Scanner sc) {
        System.out.println("\nEnter the company name:");
        dbCompanyDao.add(new Company(sc.nextLine()));
        System.out.println("The company was created!");
    }
    public String listCompanies() {
        List<Company> companyList = dbCompanyDao.findAll();
        if (companyList.isEmpty()) {
            return "The company list is empty!";
        } else {
            StringBuilder result = new StringBuilder();
            companyList.forEach(company -> result.append(company).append("\n"));
            return result.toString();
        }
    }
}
