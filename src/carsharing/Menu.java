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
    private final ManagerMenu managerMenu;
    private final CustomerMenu customerMenu;

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
        this.managerMenu = new ManagerMenu(dbService);
        this.customerMenu = new CustomerMenu(dbService);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean toContinue = true;
        while (toContinue) {
            System.out.print("1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit\n");
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 0 -> toContinue = false;
                case 1 -> managerMenu.managerMenu(sc);
                case 2 -> customerMenu.chooseCustomer(sc);
                case 3 -> customerMenu.addCustomer(sc);
            }
        }
    }


}
