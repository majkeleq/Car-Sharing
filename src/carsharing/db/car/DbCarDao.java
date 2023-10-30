package carsharing.db.car;

import carsharing.db.DbClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbCarDao implements CarDao {
    private DbClient dbClient;
    private final String CREATE_CAR = "CREATE TABLE CAR(" +
            "ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR_IGNORECASE(255) NOT NULL UNIQUE," +
            "COMPANY_ID INT NOT NULL," +
            "foreign key (COMPANY_ID) references company(ID)" +
            ")";
    private final String SELECT_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = %d";
    private final String ADD_CAR = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('%s',%d)";

    public DbCarDao(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(CREATE_CAR);
    }

    @Override
    public List<Car> findByCompany(int companyId) {
        List<Car> carList = new ArrayList<>();
        HashMap<Integer, String> result = dbClient.selectForList(String.format(SELECT_CARS, companyId));
        result.forEach((key, value) -> carList.add(new Car(value)));
        return carList;
    }

    @Override
    public void add(Car car) {
        dbClient.run(String.format(ADD_CAR, car.getName(), car.getCompanyId()));
    }
}
