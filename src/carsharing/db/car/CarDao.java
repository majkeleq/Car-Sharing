package carsharing.db.car;

import java.util.List;

public interface CarDao {
    List<Car> findByCompany(int companyId);
    List<Car> findAvailableCarsByCompany(int companyId);
    void add(Car car);
    void rentCar(Car car);
    void returnCar(int carId);
    Car getCarById(int carId);
}
