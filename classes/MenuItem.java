package classes;

// created by Maria
public class MenuItem {
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;

    public MenuItem(String name, double price, String description, int availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
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
}
