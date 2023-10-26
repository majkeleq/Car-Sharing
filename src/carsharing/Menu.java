package carsharing;

import carsharing.db.DbClient;
import carsharing.db.DbDaoImplementation;

import java.util.Scanner;

public class Menu {
    private final DbClient dbClient;
    private final DbDaoImplementation dbDaoImplementation;

    public Menu(String[] args) {
        String DB_NAME = "carsharing";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFilename")) {
                DB_NAME = args[i + 1];
                break;
            }
        }
        this.dbClient = new DbClient(DB_NAME);
        this.dbDaoImplementation = new DbDaoImplementation(dbClient);
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
                case 1 -> System.out.println(dbDaoImplementation.listCompanies());
                case 2 -> dbDaoImplementation.addCompany(sc);
            }
        }
    }
}
