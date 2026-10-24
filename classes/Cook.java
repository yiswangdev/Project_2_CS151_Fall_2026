package classes;

import Interface.Orderhandler;
import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ClassInstanceLimit;

import java.util.HashSet;
import java.util.Set;

// Created by: Margarita
public class Cook extends Employee implements Orderhandler {

    private Order currentOrder;
    private String foodCharacteristic;
    private static final Set<Cook> instances = new HashSet<>();

    public Cook(String name, String employeeID, String characteristic)
            throws DuplicateObjectException, InstanceLimitException {
        super(name, employeeID);
        foodCharacteristic = characteristic;
        ClassInstanceLimit.checkLimit("Cook");
        for (Cook cook : instances) {
            if (cook.getName().equals(name) && cook.getEmployeeID().equals(employeeID)) {
                throw new DuplicateObjectException("Duplicate cook: " + employeeID);
            }
        }
        instances.add(this);
        if (instances.size() > 100) {
            throw new InstanceLimitException("Too many cooks created! - " + instances.size());
        }
    }

    public boolean canHandleOrder(Order order) {
        return order != null && isAvailableForWork();
    }

    public void acceptOrder(Order order) {
        if (canHandleOrder(order)) {
            currentOrder = order;
            order.assignCook(this);
            assignTask();
        }
    }

    public void updateOrderStatus(Order order) {
        order.updateStatus("PREPARING");
        for (OrderItem item : currentOrder.getOrders()) {
            item.setCharacteristic(foodCharacteristic);
        }
    }

    public void finishOrder(Order order) {
        if (currentOrder != null) {

            order.updateStatus("READY");
            currentOrder = null;

            completeTask();
            System.out.println(
                    "Order " + order.getId() + " is ready to be picked up by " + order.getServer().getName() + ".");
        }
    }

    public void prepareOrder(Order order) {
        if (canHandleOrder(order)) {
            acceptOrder(order);
            updateOrderStatus(order);
            System.out.println("Cook " + getName() + " is preparing order " + order.getId() + ".");
        }
    }

    public void addSpecialCharacteristic(OrderItem item) {
        if (item != null) {
            item.setCharacteristic(foodCharacteristic);
            System.out.println("Your item has been customized: " + item.getDescription());
        }
    }

    @Override
    public String toString() {
        int inProgressCount = 1;
        if (currentOrder == null) {
            inProgressCount = 0;
        }
        return "Cook " + super.toString() + ", orders in progress: " + inProgressCount;
    }
}