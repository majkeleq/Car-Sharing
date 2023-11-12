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
            "AVAILABLE BOOLEAN NOT NULL," +
            "foreign key (COMPANY_ID) references company(ID)" +
            ")";
    private final String SELECT_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = %d";
    private final String SELECT_AVAILABLE_CARS = "SELECT * FROM CAR WHERE COMPANY_ID = %d AND AVAILABLE IS TRUE";
    private final String ADD_CAR = "INSERT INTO CAR (NAME, COMPANY_ID, AVAILABLE) VALUES ('%s',%d, TRUE)";
    private final String RENT_CAR = "UPDATE CAR SET AVAILABLE = FALSE WHERE ID = %d";
    private final String RETURN_CAR = "UPDATE CAR SET AVAILABLE = TRUE WHERE ID = %d";

    public DbCarDao(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(CREATE_CAR);
    }

    @Override
    public List<Car> findByCompany(int companyId) {
        List<Car> carList = new ArrayList<>();
        HashMap<Integer, String> result = dbClient.selectForList(String.format(SELECT_CARS, companyId));
        result.forEach((key, value) -> carList.add(new Car(key, value)));
        return carList;
    }

    @Override
    public List<Car> findAvailableCarsByCompany(int companyId) {
        List<Car> carList = new ArrayList<>();
        HashMap<Integer, String> result = dbClient.selectForList(String.format(SELECT_AVAILABLE_CARS, companyId));
        result.forEach((key, value) -> carList.add(new Car(key, value)));
        return carList;
    }

    @Override
    public void add(Car car) {
        dbClient.run(String.format(ADD_CAR, car.getName(), car.getCompanyId()));
    }

    @Override
    public void rentCar(Car car) {
        dbClient.run(String.format(RENT_CAR, car.getId()));
    }

    @Override
    public void returnCar(Car car) {
        dbClient.run(String.format(RETURN_CAR, car.getId()));
    }
}
