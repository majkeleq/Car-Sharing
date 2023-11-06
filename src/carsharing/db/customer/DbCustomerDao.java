package carsharing.db.customer;

import carsharing.db.DbClient;
import carsharing.db.company.Company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbCustomerDao implements CustomerDao{
    private DbClient dbClient;
    private final String CREATE_CUSTOMER = "CREATE TABLE CUSTOMER(" +
            "ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR_IGNORECASE(255) NOT NULL UNIQUE," +
            "RENTED_CAR_ID INT," +
            "foreign key (RENTED_CAR_ID) references car(ID)" +
            ")";
    private final String ADD_CUSTOMER = "INSERT INTO CUSTOMER (NAME, RENTED_CAR_ID) VALUES ('%s', null)";
    private final String SELECT_CUSTOMER = "SELECT * FROM CUSTOMER WHERE ID = %d";
    private final String SELECT_CUSTOMERS = "SELECT * FROM CUSTOMER";

    public DbCustomerDao(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(CREATE_CUSTOMER);
    }
    @Override
    public void add(Customer customer) {
        dbClient.run(String.format(ADD_CUSTOMER, customer.getName()));
    }

    @Override
    public Customer findCustomer(int id) {
        Map.Entry<Integer, String> entry = dbClient.select(String.format(SELECT_CUSTOMER, id));
        return new Customer(entry.getKey(), entry.getValue());
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        HashMap<Integer,String> result = dbClient.selectForList(SELECT_CUSTOMERS);
        result.forEach((key, value) -> customerList.add(new Customer(key, value)));
        return customerList;
    }
}
