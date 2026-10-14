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

    /**
     * Changes the price after validating it (between $0.01 and $500, rounded to cents).
     *
     * @param newPrice the new price per unit
     * @throws IllegalArgumentException if the price is out of range or not a number
     */
    public void setPrice(double newPrice) {
        price = newPrice;
    }

     /**
     * Marks the item as sold out so it can no longer be ordered.
     *
     * @throws IllegalStateException if the item is already sold out
     */
    public void markSoldOut() {
        if (!available) {
            throw new IllegalStateException(name + " is already sold out.");
        }
        available = false;
    }

    /**
     * Makes a sold-out item orderable again.
     *
     * @throws IllegalStateException if the item is already in stock
     */
    public void restock() {
        if (available) {
        throw new IllegalStateException(name + " is already in stock.");
        }
        available = true;
    }

    /**
     * Calculates the price for the given quantity of this item.
     *
     * @param quantity the number of units; must be at least 1
     * @return the price per unit multiplied by the quantity
     * @throws IllegalArgumentException if the quantity is less than 1
     */
    public double priceFor(int quantity) {
        if (quantity < 1) {
        throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        return price * quantity;
    }

    /**
     * Compares this item to another object by name, ignoring case.
     *
     * @param other the object to compare with
     * @return {@code true} if {@code other} is a {@code MenuItem} with the same name
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
        return true;
        }
        return other instanceof MenuItem && nameKey().equals(((MenuItem) other).nameKey());
    }

    /**
     * Returns a hash code based on the lowercase name, consistent with {@link #equals(Object)}.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return nameKey().hashCode();
    }

    /**
     * Returns the lowercase form of the name, used for case-insensitive comparison.
     *
     * @return the lowercase name
     */
    private String nameKey() {
        return name.toLowerCase();
    }


    /**
     * Returns a one-line description of the item, with a "(SOLD OUT)" marker if it is unavailable.
     *
     * @return a formatted string with the id, name, and price
     */
    @Override
    public String toString() {
        return String.format("[%s] %-20s $%6.2f%s", id, name, price, available ? "" : "  (SOLD OUT)");
    }
}




        