package classes;

import Interface.Orderhandler;
import utils.ClassInstanceLimit;

import java.util.ArrayList;

public class Cook extends Employee implements Orderhandler {

    private ArrayList<Order> currentOrders;

    public Cook(String name, String employeeID) {
        super(name, employeeID);
        ClassInstanceLimit.checkLimit("Cook");
        currentOrders = new ArrayList<>();
    }

    public boolean canHandleOrder(Order order) {
        return order != null && isAvailableForWork();
    }

    public void acceptOrder(Order order) {
        if (canHandleOrder(order)) {
            currentOrders.add(order);
            order.assignCook(this);
            assignTask();
        }
    }

    public void updateOrderStatus(Order order) {
        if (currentOrders.contains(order)) {
            order.updateStatus("PREPARING");
        }
    }

    public void finishOrder(Order order) {
        if (currentOrders.contains(order)) {
            order.updateStatus("READY");
            currentOrders.remove(order);
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

    public void addSpecialCharacteristic(OrderItem item, String characteristic) {
        if (item != null && characteristic != null && !characteristic.isBlank()) {
            item.addCharacteristic(characteristic);
            System.out.println("Your item has been customized: " + item.getDescription());
        }
    }

    @Override
    public String toString() {
        return "Cook " + super.toString() + ", orders in progress: " + currentOrders.size();
    }
}