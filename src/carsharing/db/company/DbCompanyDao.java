package carsharing.db.company;

import carsharing.db.DbClient;
import carsharing.db.car.Car;

import java.util.*;

public class DbCompanyDao implements CompanyDao {
    private DbClient dbClient;

    private final String CREATE_COMPANY = "CREATE TABLE COMPANY(" +
            "ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR_IGNORECASE(255) NOT NULL UNIQUE)";
    private final String SELECT_COMPANIES = "SELECT * FROM COMPANY";
    private final String SELECT_COMPANY = "SELECT * FROM COMPANY WHERE ID = %d";

    private final String ADD_COMPANY = "INSERT INTO COMPANY (NAME) VALUES ('%s')";
    private final String SELECT_COMPANY_BY_CAR = "SELECT company.* FROM car" +
            " JOIN company ON company.id = car.company_id" +
            " WHERE car.id = %d";
    public DbCompanyDao(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(CREATE_COMPANY);
    }

    public void setDbClient(DbClient dbClient) {
        this.dbClient = dbClient;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companyList = new ArrayList<>();
        HashMap<Integer,String> result = dbClient.selectForList(SELECT_COMPANIES);
        result.forEach((key, value) -> companyList.add(new Company(key, value)));
        return companyList;
        //return dbClient.selectCompanies(SELECT_COMPANIES);
    }

    @Override
    public void add(Company company) {
        dbClient.run(String.format(ADD_COMPANY, company.getName()));
    }

    @Override
    public Company findById(int id) {
        Map.Entry<Integer, String> entry = dbClient.select(String.format(SELECT_COMPANY, id));
        return new Company(entry.getKey(), entry.getValue());
        //return dbClient.select(String.format(SELECT_COMPANY, id));
    }

    @Override
    public Company findByCar(Car car) {
        Map.Entry<Integer, String> entry = dbClient.select(String.format(SELECT_COMPANY_BY_CAR,car.getId()));
        return new Company(entry.getKey(), entry.getValue());
    }
}
