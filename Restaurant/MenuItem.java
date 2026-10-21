package Restaurant;

public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;

    public MenuItem(String id, String name, double price, String description, int availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
    }

    /** 
     * Returns the name of this item
     * @return the trimmed item name
    */
    public String getName() {
        return name;
    }

    /**
     * Checks whether at least one serving is in stock.
     *
     * @return {@code true} if at least one serving is available, {@code false} if it is sold out
     */
    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    /**
     * Checks whether enough servings are available for the requested quantity.
     *
     * @param quantity the number of servings requested; must be at least 1
     * @return {@code true} if the requested quantity is in stock
     * @throws IllegalArgumentException if the quantity is less than 1
     */
    public boolean canFulfillOrder(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        return quantity <= availableQuantity;
    }

    /**
     * Formats the item's name, price, and description for the menu.
     *
     * @return a formatted string such as "Burger - $9.50: Beef patty with cheese"
     */
    public String formatMenuDisplay() {
        return String.format("%s - $%.2f: %s", name, price, description);
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
        if (!isAvailable()) {
            throw new IllegalStateException(name + " is already sold out.");
        }
        availableQuantity = 0;
    }

    /**
     * Makes a sold-out item orderable again by setting its stock.
     *
     * @param quantity the number of servings now in stock; must be at least 1
     * @throws IllegalStateException if the item is already in stock
     * @throws IllegalArgumentException if the quantity is less than 1
     */
    public void restock(int quantity) {
        if (isAvailable()) {
            throw new IllegalStateException(name + " is already in stock.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        availableQuantity = quantity;
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
        return String.format("[%s] %-20s $%6.2f%s", id, name, price, isAvailable() ? "" : "  (SOLD OUT)");
    }
}




        
