package src.classes;

import java.util.HashSet;

import src.exceptions.DuplicateObjectException;
import src.exceptions.InstanceLimitException;
import src.exceptions.QuantityUnavailableException;
import src.utils.ClassInstanceLimit;
import src.utils.DuplicateInstancesCheck;

// Created by: Margarita
public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String characteristic;
    private static final HashSet<Object> instances = new HashSet<>();

    public OrderItem(MenuItem menuItem, int quantity) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "OrderItem");
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "OrderItem");
        instances.add(this);
    }

    @Override 
    public boolean equals(Object other){
        OrderItem otherOrderItem = (OrderItem) other;
        return otherOrderItem.menuItem.equals(menuItem) && otherOrderItem.quantity == quantity;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public double calculateSubtotal() {
        try {
            return menuItem.priceFor(quantity);

        } catch (QuantityUnavailableException e) {
            System.out.println(e.getMessage());

        }

        // fallback method to get price
        return menuItem.getPrice() * quantity;
        
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