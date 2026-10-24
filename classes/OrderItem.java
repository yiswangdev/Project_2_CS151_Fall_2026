package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;

// Created by: Margarita
public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String characteristic;
    private static final Set<OrderItem> instances = new HashSet<>();

    public OrderItem(MenuItem menuItem, int quantity) throws DuplicateObjectException, InstanceLimitException {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
        for (OrderItem orderItem : instances) {
            if (orderItem.menuItem.equals(menuItem) && orderItem.quantity == quantity) {
                throw new DuplicateObjectException("Duplicate order: " + quantity + " " + menuItem);
            }
        }
        instances.add(this);
        if (instances.size() > 100) {
            throw new InstanceLimitException("Too many order items created! - " + instances.size());
        }
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public double calculateSubtotal() {
        return menuItem.priceFor(quantity);
    }

    public String getDescription() {
        String charString = "";
        if (characteristic != null) {
            charString = characteristic + " ";
        }
        String description = quantity + "x " + charString;
        return description + menuItem.getName();
    }

    @Override
    public String toString() {
        return getDescription() + " - $" + String.format("%.2f", calculateSubtotal());
    }
}