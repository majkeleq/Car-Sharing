package carsharing.db.company;


import carsharing.db.car.Car;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    void add(Company company);
    Company findById(int id);
    Company findByCar(Car car);
}
