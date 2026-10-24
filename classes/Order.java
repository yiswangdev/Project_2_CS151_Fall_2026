package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ClassInstanceLimit;
import utils.DuplicateInstancesCheck;

// Created by: Isaac
public class Order {
    public static final int MAX_QUANTITY_PER_ITEM = 20;
    private String id;
    private Customer customer;
    private Server server;
    private ArrayList<OrderItem> items;
    private Cook cook;
    private String status = "DRAFT";
    private static final HashSet<Object> instances = new HashSet<>();


    public Order(Customer customer, Server server) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "Order");
        this.id = "" + instances.size()+1;
        this.customer = customer;
        this.server = server;
        items = new ArrayList<>();
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "Order");
        instances.add(this);
    }

    @Override 
    public boolean equals(Object other){
        Order otherOrder = (Order) other;
        return otherOrder.getId().equals(id) && otherOrder.customer.equals(customer) && otherOrder.getServer().equals(server);
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Server getServer() {
        return server;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }

    public void assignCook(Cook cook) {
        this.cook = cook;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculateSubtotal();
        }
        return total;
    }

    public String getOrderSummary() {
        return this.toString() + "Total: $" + String.format("%.2f", calculateTotal());
    }

    ArrayList<OrderItem> getOrders() {
        return items;
    }

    @Override
    public String toString() {
        String str = "";
        for (OrderItem item : items) {
            str += item.getDescription() + "\n";
        }
        return str;
    }
}