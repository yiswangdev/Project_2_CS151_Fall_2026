package classes;

import Interface.Orderhandler;
import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ClassInstanceLimit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;

public class Cook extends Employee implements Orderhandler {

    private ArrayList<Order> currentOrders;
    //private static int cookCounter = 0;
    private static final Set<Cook> instances = new HashSet<>();

    public Cook(String name, String employeeID) throws Exception{
        super(name, employeeID);
        ClassInstanceLimit.checkLimit("Cook");
        currentOrders = new ArrayList<>();
        //cookCounter++;
        for (Cook cook : instances) {
            if (cook.getName().equals(name) && cook.getEmployeeID().equals(employeeID)) {
                throw new DuplicateObjectException("Duplicate cook: " + employeeID);
            }
        }
        instances.add(this);
        if(instances.size() > 100){
            throw new InstanceLimitException("Too many cooks created! - " + instances.size());
        }
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
        finishPreparation(order);
    }

    public void finishPreparation(Order order) {
        if (currentOrders.contains(order)) {
            order.updateStatus("READY");
            currentOrders.remove(order);
            completeTask();
            System.out.println("Order " + order.getId() + " is ready to be picked up by " + order.getServer().getName() + ".");
        }
    }

    public void prepareOrder(Order order) {
        if (canHandleOrder(order)){
            acceptOrder(order);
            updateOrderStatus(order);
            System.out.println("Cook " + getName() + " is preparing order " + order.getId() + ".");
        }
    }

    public void addSpecialCharacteristic(OrderItem item, String characteristic) {
        if (item != null && characteristic != null && !characteristic.isBlank()){
            item.addCharacteristic(characteristic);
            System.out.println("Your item has been customized: " + item.getDescription());
        }
    }
}