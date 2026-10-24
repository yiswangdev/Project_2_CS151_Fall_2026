package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ClassInstanceLimit;
import utils.DuplicateInstancesCheck;

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
        return menuItem.priceFor(quantity);
    }

    public String getDescription() {
        String charString = "";
        if(characteristic != null){
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