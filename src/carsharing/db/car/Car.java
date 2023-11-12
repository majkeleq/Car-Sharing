package carsharing.db.car;

public class Car {
    private final String name;
    private int companyId;
    private int id;

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
    }
    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }
    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }
}

