package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;

// created by Maria
public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;
    //private static int menuItemCounter = 0;

    private static final Set<MenuItem> instances = new HashSet<>();


    public MenuItem(String name, double price, String description, int availableQuantity) throws Exception{
        this(null, name, price, description, availableQuantity);
        //menuItemCounter++;
    }

    public MenuItem(String id, String name, double price, String description, int availableQuantity) throws Exception {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
        //menuItemCounter++;
        for (MenuItem item : instances) {
            if (item.name.equals(name) && Double.compare(item.price, price) == 0 && item.description.equals(description) && item.availableQuantity == availableQuantity) {
                throw new DuplicateObjectException("Duplicate menu item: " + name);
            }
        }
        instances.add(this);
        if(instances.size() > 100){
            throw new InstanceLimitException("Too many menu items created! - " + instances.size());
        }
    }

    public String getId() {
        return id;
    }

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

    public void fulfillOrder(int quantity) {
        if (!canFulfillOrder(quantity)) {
            throw new IllegalArgumentException("Not enough servings available.");
        }
        availableQuantity -= quantity;
    }

    public double priceFor(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        return price * quantity;
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
