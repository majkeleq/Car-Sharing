package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DbClient {
    private final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:./src/carsharing/db/"; //check
    //private final String DB_URL = "jdbc:h2:./Car Sharing/task/src/carsharing/db/"; //RUN
    private final String DB_NAME;
    private final String DROP_COMPANY = "DROP TABLE IF EXISTS COMPANY";
    private final String DROP_CAR = "DROP TABLE IF EXISTS CAR";

    public DbClient(String DB_NAME) {
        this.DB_NAME = DB_NAME;
        run(DROP_CAR);
        run(DROP_COMPANY);
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

    /*
    public List<Company> selectCompanies(String query) {
        List<Company> companyList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            try (ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    companyList.add(new Company(rs.getInt("ID"), rs.getString("NAME")));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }*/

    /*
    public Company select(String query) {
        List<Company> companyList = selectForList(query);
        if (companyList.size() == 1) {
            return companyList.get(0);
        } else if (companyList.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    } */

    public Map.Entry<Integer, String> select(String query) {
        HashMap<Integer, String> list = selectForList(query);
        if (list.size() == 1) {
            return list.entrySet().iterator().next();
        } else if (list.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    public HashMap<Integer, String> selectForList(String query) {
        HashMap<Integer, String> list = new HashMap<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
                 Statement st = conn.createStatement()) {
                conn.setAutoCommit(true);
                try (ResultSet rs = st.executeQuery(query)) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("NAME");
                        list.put(id, name);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /*public List<Company> selectForList(String query) {
        List<Company> companyList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);
            try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME);
                 Statement st = conn.createStatement()) {
                conn.setAutoCommit(true);
                try (ResultSet rs = st.executeQuery(query)) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("NAME");
                        Company company = new Company(id, name);
                        companyList.add(company);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }
     */
}
