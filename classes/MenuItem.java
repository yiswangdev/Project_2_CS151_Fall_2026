package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import exceptions.QuantityUnavailableException;

// created by Maria
public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;
    //private static int menuItemCounter = 0;

    private static final Set<MenuItem> instances = new HashSet<>();


    public MenuItem(String name, double price, String description, int availableQuantity) throws DuplicateObjectException, InstanceLimitException{
        this(null, name, price, description, availableQuantity);
        //menuItemCounter++;
    }

    public MenuItem(String id, String name, double price, String description, int availableQuantity) throws DuplicateObjectException, InstanceLimitException{
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

    public void checkQuantity() throws QuantityUnavailableException {
        if (availableQuantity <= 0 || !canFulfillOrder(availableQuantity)) {
            throw new QuantityUnavailableException("Sorry, we can't make that many " + getName());
        }
    }

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

    public String formatMenuDisplay() {
        return String.format("%s - $%.2f: %s", name, price, description);
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}
