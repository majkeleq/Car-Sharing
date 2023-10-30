package carsharing.db.company;


import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    void add(Company company);
    Company findById(int id);
}
