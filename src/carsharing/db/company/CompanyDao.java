package carsharing.db.company;

import carsharing.db.company.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    void add(Company company);
    Company findById(int id);
}
