package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

public class Cook extends Employee implements Orderhandler {

    private ArrayList<Order> currentOrders;
    private static int cookCounter = 0;

    public Cook(String name, String employeeID) {
        super(name, employeeID);
        currentOrders = new ArrayList<>();
        cookCounter++;
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
            System.out.println("Your order is ready for pickup.");
        }
    }

    public void prepareOrder(Order order) {
        if (canHandleOrder(order)){
            acceptOrder(order);
            updateOrderStatus(order);
            System.out.println(getName() + " is preparing your order...");
        }
    }

    public void addSpecialCharacteristic(OrderItem item, String characteristic) {
        if (item != null && characteristic != null && !characteristic.isBlank()){
            item.addCharacteristic(characteristic);
            System.out.println("Your item has been customized: " + item.getDescription());
        }
    }
}