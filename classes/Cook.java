package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

public class Cook extends Employee {

    private ArrayList<Order> currentOrders;

    public Cook(String name, String employeeID) {
        super(name, employeeID);
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
}