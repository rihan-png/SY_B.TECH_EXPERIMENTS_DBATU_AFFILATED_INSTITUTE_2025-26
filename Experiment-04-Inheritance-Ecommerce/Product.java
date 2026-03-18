// Base class
public class Product {
    protected int productID;
    protected String name;
    protected double price;

    public Product(int productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    public void applyDiscount() {
        System.out.println("No discount available.");
    }
}

// Subclass Electronics
class Electronics extends Product {
    private int warrantyPeriod;

    public Electronics(int productID, String name, double price, int warrantyPeriod) {
        super(productID, name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void applyDiscount() {
        double discount = price * 0.15;
        System.out.println("Discounted price for " + name + ": " + (price - discount));
    }
}

// Subclass Clothing
class Clothing extends Product {
    private String size;

    public Clothing(int productID, String name, double price, String size) {
        super(productID, name, price);
        this.size = size;
    }

    @Override
    public void applyDiscount() {
        double discount = price * 0.20;
        System.out.println("Discounted price for " + name + ": " + (price - discount));
    }
}

// Subclass Groceries
class Groceries extends Product {
    private String expiryDate;

    public Groceries(int productID, String name, double price, String expiryDate) {
        super(productID, name, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public void applyDiscount() {
        double discount = price * 0.10;
        System.out.println("Discounted price for " + name + ": " + (price - discount));
    }
}
