package carsharing.db.car;

public class Car {
    private final String name;
    private int companyId;

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
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
}

