package carsharing.db.customer;

public class Customer {
    private final String name;
    private int id;
    public Customer(String name) {
        this.name = name;
    }

    public Customer(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
