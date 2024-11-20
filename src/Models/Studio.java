package Models;

public class Studio {
    private int id;
    private String name;
    private double basePrice;

    public Studio(int id, String name, double basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getbasePrice() {
        return this.basePrice;
    }
}
