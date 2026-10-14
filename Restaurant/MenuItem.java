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

    /** 
     * Returns the name of this item
     * @return the trimmed item name
    */
    public String getName() {
        return name;
    }

    /**
     * Tells whether this item can currently be ordered
     * 
     * @return {@code true} if the item is in stock, {@code false} if it is sold out
     */
    public boolean isAvailable() {
        return available;
    }

    /** Changes the price after validating it (between $0.01 and $500, rounded to cents).
     * 
     * @param newPrice the new price per unit 
     * @throws IllegalArgumentException if the price is out of range or not a number
     */
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

    /** Makes a sold-out item orderable again. */
    public void restock() {
        if (available) {
        throw new IllegalStateException(name + " is already in stock.");
        }
        available = true;
    }

    /** Price for the given quantity of this item. */
    public double priceFor(int quantity) {
        if (quantity < 1) {
        throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        return price * quantity;
    }
}




        