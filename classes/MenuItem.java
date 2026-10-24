package classes;

// created by Maria
public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;
    private static int menuItemCounter = 0;


    public MenuItem(String name, double price, String description, int availableQuantity) {
        this(null, name, price, description, availableQuantity);
        menuItemCounter++;
    }

    public MenuItem(String id, String name, double price, String description, int availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
        menuItemCounter++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    public boolean canFulfillOrder(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        return quantity <= availableQuantity;
    }

    public String formatMenuDisplay() {
        return String.format("%s - $%.2f: %s", name, price, description);
    }

    @Override 
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}
