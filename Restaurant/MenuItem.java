package Restaurant;

public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private boolean available = true;

    public  MenuItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    /** Changes the price after validating it (between $0.01 and $500, rounded to cents). */
    public void setPrice(double newPrice) {
        price = newPrice;
    }

    /** Marks the item as sold out so it can no longer be ordered. */
    public void markSoldOut() {
        if (!available) {
            throw new IllegalStateException(name + " is already sold out.");
        }
        available = false;
    }
}




        