package carsharing;

import carsharing.db.DbService;
import carsharing.db.car.Car;
import carsharing.db.company.Company;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ManagerMenu {
    private final DbService dbService;

    public ManagerMenu(DbService dbService) {
        this.dbService = dbService;
    }

    public void managerMenu(Scanner sc) {
        boolean toContinue = true;
        while (toContinue) {
            System.out.print("1. Company list\n2. Create a company\n0. Back\n");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 0 -> toContinue = false;
                case 1 -> listCompanies(sc);
                case 2 -> dbService.addCompany(sc);
            }
        }
    }

    private void listCompanies(Scanner sc) {
        List<Company> companyList = dbService.findAllCompanies();
        int input = dbService.getContext(companyList, "company", sc);
        while ( input != 0) {
            companyContext(sc, dbService.findCompanyById(input));
            input = dbService.getContext(companyList, "company", sc);
        }
    }

    private void companyContext(Scanner sc, Company company) {
        System.out.printf("'%s' company:", company.getName());
        boolean toContinue = true;
        while (toContinue) {
            System.out.print("\n1. Car list\n2. Create a car\n0. Back\n");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1 -> {
                    List<Car> carList = dbService.findAllCompanyCars(company);
                    if (!carList.isEmpty()) {
                        AtomicInteger index = new AtomicInteger(1);
                        carList.forEach(c -> System.out.printf("%d. %s\n", index.getAndIncrement(),c));
                    } else {
                        System.out.print("\nThe car list is empty!\n");
                    }
                }
                case 2 -> dbService.addCar(sc, company);

                case 0 -> toContinue = false;
            }
        }
    }
}
