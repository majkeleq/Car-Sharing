package carsharing.db;

import java.util.List;

public class DbCompanyDao implements CompanyDao {
    private DbClient dbClient;
    private final String INIT_DROP = "DROP TABLE IF EXISTS COMPANY";
    private final String CREATE_COMPANY = "CREATE TABLE COMPANY(" +
            "ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR_IGNORECASE(255) NOT NULL UNIQUE)";
    private final String SELECT_COMPANIES = "SELECT * FROM COMPANY";

    public DbCompanyDao(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(INIT_DROP);
        dbClient.run(CREATE_COMPANY);
    }

    public void setDbClient(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public List<Company> findAll() {
        return dbClient.selectCompanies(SELECT_COMPANIES);
    }

    @Override
    public void add(Company company) {
        String query = String.format("INSERT INTO COMPANY (NAME) VALUES ('%s')", company.getName());
        dbClient.run(query);
    }
}
