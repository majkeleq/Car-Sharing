package carsharing;

import carsharing.db.DatabaseCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String DB_NAME = "carsharing";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFilename")) {
                DB_NAME = args[i + 1];
                break;
            }
        }
        new DatabaseCreator().run(DB_NAME);
    }
}