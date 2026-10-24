package src.classes;

import java.util.HashSet;

import src.exceptions.DuplicateObjectException;
import src.exceptions.InstanceLimitException;
import src.exceptions.QuantityUnavailableException;
import src.utils.ClassInstanceLimit;
import src.utils.DuplicateInstancesCheck;

// Created by: Eric Wang
public class Customer {

    private String name;
    private Table table;
    private Order order;
    private static final HashSet<Object> instances = new HashSet<>();

    public Customer(String name) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "Customer");
        this.name = name;
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "Customer");
        instances.add(this);
    }

    @Override 
    public boolean equals(Object other){
        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(name) && otherCustomer.table == table && otherCustomer.order == order;
    }

    public String getName() {
        return name;
    }

    public void sitAtTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("Table cannot be null.");
        }
        this.table = table;
    }

    public void placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        this.order = order;
        order.setCustomer(this);
    }

    public void requestAdditionalItem(MenuItem item, int quantity)
            throws IllegalArgumentException, DuplicateObjectException, InstanceLimitException, QuantityUnavailableException {
        if (order == null) {
            System.out.println("Place an order before requesting another item.");
            return;
        }
        if (item == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }
        item.fulfillOrder(quantity);
        order.addItem(new OrderItem(item, quantity));
    }

    public void leaveRestaurant() {
        if (table != null) {
            table.releaseTable();
        }
        table = null;
        order = null;
    }

    @Override
    public String toString() {
        return name;
    }
}
