package carsharing;

import carsharing.db.car.Car;
import carsharing.db.company.Company;
import carsharing.db.DbClient;
import carsharing.db.DbService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private final DbClient dbClient;
    private final DbService dbService;

    public Menu(String[] args) {
        String DB_NAME = "carsharing";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFilename")) {
                DB_NAME = args[i + 1];
                break;
            }
        }
        this.dbClient = new DbClient(DB_NAME);
        this.dbService = new DbService(dbClient);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean toContinue = true;
        while (toContinue) {
            System.out.print("1. Log in as a manager\n0. Exit\n");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 0 -> toContinue = false;
                case 1 -> managerMenu(sc);
            }
        }
    }

    private void managerMenu(Scanner sc) {
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
        List<Company> companyList = dbService.listCompanies();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            StringBuilder result = new StringBuilder();
            while (true) {
                result.setLength(0);
                result.append("Choose company:\n");
                companyList.forEach(company -> result.append(company).append("\n"));
                result.append("0. Back");
                System.out.println(result);
                int input = Integer.parseInt(sc.nextLine());
                if (input == 0) {
                    break;
                }
                if (companyList.stream().map(Company::getId).filter(id -> id == input).count() == 1) {
                    companyContext(sc, dbService.findById(input));
                    break;
                }
            }
        }
        //System.out.println(dbService.listCompanies());
    }

    private void companyContext(Scanner sc, Company company) {
        System.out.printf("'%s' company:", company.getName());
        boolean toContinue = true;
        while (toContinue) {
            System.out.print("\n1. Car list\n2. Create a car\n0. Back\n");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1 -> {
                    List<Car> carList = dbService.listCars(company);
                    if (!carList.isEmpty()) {
                        AtomicInteger index = new AtomicInteger(1);
                        carList.forEach(c -> {
                            System.out.printf("%d. %s\n", index.getAndIncrement(),c);
                        });
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
