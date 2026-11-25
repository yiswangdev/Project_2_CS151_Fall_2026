package src.classes;

import java.util.*;

import src.Interface.Orderhandler;
import src.exceptions.*;
import src.utils.*;

public class Busser extends Employee implements Orderhandler {
    private final String greeting;
    private final ArrayList<Table> assignedTables;
    private Order activeOrder;
    private static final HashSet<Object> instances = new HashSet<>();

    public Busser(String name, String employeeID, String greeting) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "Busser");
        super(name, employeeID);
        this.greeting = greeting;
        this.assignedTables = new ArrayList<>();
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "Busser");
        instances.add(this);
    }

    public String greetTable() {
        return greeting;
    }

     public String deliverOrder(Order order) {
        finishOrder(order);
        return "Busser " + getName() + " delivered order " + order.getId() + " to ";
    }

    @Override
    public boolean canHandleOrder(Order order) {
        return order != null && isAvailableForWork();
    }

    @Override
    public void acceptOrder(Order order) {
        if (!canHandleOrder(order)) {
            throw new IllegalArgumentException("Order cannot be accepted by " + getName());
        }
        activeOrder = order;
        order.assignBusser(this);
        assignTask();
    }

    @Override
    public void updateOrderStatus(Order order) {
        if (order == null || order != activeOrder) {
            throw new IllegalArgumentException("Order is not being handled by " + getName());
        }
        order.updateStatus("PICKED_UP");
        System.out.println("Busser " + getName() + " picked up order " + order.getId() + " from the kitchen.");
    }

    @Override
    public void finishOrder(Order order) {
        if (order == null || order != activeOrder) {
            throw new IllegalArgumentException("Order is not being handled by " + getName());
        }
        order.updateStatus("DELIVERED");
        activeOrder = null;
        completeTask();
    }

    @Override
    public String toString() {
        return "Busser " + super.toString() + ", delivering: " + (activeOrder == null ? "none" : activeOrder.getId());
    }
}




