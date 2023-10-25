package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:./src/carsharing/db/"; //check
    //private final String DB_URL = "dbc:h2:./Car Sharing/task/src/carsharing/db/"; //RUN
    private final String DB_NAME;
    private final String INIT_DROP = "DROP TABLE IF EXISTS COMPANY";
    private final String CREATE_COMPANY = "CREATE TABLE COMPANY(" +
            "ID INT NOT NULL," +
            "NAME VARCHAR_IGNORECASE(255) NOT NULL)";

    public Database(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public void run() {
        try {
            Class.forName("org.h2.Driver");
            try (
                    Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
                    Statement st = conn.createStatement()) {
                conn.setAutoCommit(true);
                st.executeUpdate(INIT_DROP);
                st.executeUpdate(CREATE_COMPANY);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
