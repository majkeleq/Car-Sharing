package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCreator {
    public void run(String DB_NAME) {
        try {
            Class.forName("org.h2.Driver");
            try (
                    Connection conn = DriverManager.getConnection("jdbc:h2:./src/carsharing/db/" + DB_NAME);//tests
                    //Connection conn = DriverManager.getConnection("jdbc:h2:./Car Sharing/task/src/carsharing/db/" + DB_NAME);//RUN
                    Statement st = conn.createStatement()) {
                conn.setAutoCommit(true);
                st.executeUpdate("DROP TABLE IF EXISTS COMPANY");
                st.executeUpdate("CREATE TABLE COMPANY(" +
                        "ID INT NOT NULL," +
                        "NAME VARCHAR_IGNORECASE(255) NOT NULL)");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
