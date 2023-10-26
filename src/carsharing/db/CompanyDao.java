package carsharing.db;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    void add(Company company);
}
