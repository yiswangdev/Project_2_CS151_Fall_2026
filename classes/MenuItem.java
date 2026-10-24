package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import exceptions.QuantityUnavailableException;
import utils.ClassInstanceLimit;
import utils.DuplicateInstancesCheck;

// Created by: Maria
public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private String description;
    private int availableQuantity;

    private static final HashSet<Object> instances = new HashSet<>();

    public MenuItem(String name, double price, String description, int availableQuantity)
            throws DuplicateObjectException, InstanceLimitException {
        this(null, name, price, description, availableQuantity);
    }

    public MenuItem(String id, String name, double price, String description, int availableQuantity) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "MenuItem");
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "MenuItem");
        instances.add(this);
    }

    @Override 
    public boolean equals(Object other){
        MenuItem otherMenuItem = (MenuItem) other;
        return otherMenuItem.name.equals(name) && Double.compare(otherMenuItem.price, price) == 0 && otherMenuItem.description.equals(description) && otherMenuItem.availableQuantity == availableQuantity;
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
