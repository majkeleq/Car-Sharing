package carsharing.db.car;

import java.util.List;

public interface CarDao {
    List<Car> findByCompany(int companyId);
    void add(Car car);
}
