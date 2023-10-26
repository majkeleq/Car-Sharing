package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbClient {
    private final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:./src/carsharing/db/"; //check
    //private final String DB_URL = "jdbc:h2:./Car Sharing/task/src/carsharing/db/"; //RUN
    private final String DB_NAME;

    public DbClient(String DB_NAME) {
        this.DB_NAME = DB_NAME;
    }

    public void run(String query) {
        try {
            Class.forName(DRIVER_CLASS_NAME);
            try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
                 Statement st = conn.createStatement()) {
                conn.setAutoCommit(true);
                st.executeUpdate(query);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Company> selectCompanies(String query) {
        List<Company> companyList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            try (ResultSet rs =st.executeQuery(query)){
                while (rs.next()) {
                    companyList.add(new Company(rs.getInt("ID"), rs.getString("NAME")));
                }
            }
         }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }
}
