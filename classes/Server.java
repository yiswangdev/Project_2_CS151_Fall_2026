package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

//created by Maria
public class Server extends Employee implements Orderhandler {
    private final String greeting;
    private final ArrayList<Table> assignedTables;
    private Order activeOrder;
    private static int serverCounter = 0;

    public Server(String name, String employeeID, String greeting) {
        super(name, employeeID);
        this.greeting = greeting;
        this.assignedTables = new ArrayList<>();
        serverCounter++;
    }

    public String greetCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        return greeting + " I'm " + getName();
    }

    public void seatCustomer(Customer customer, Table table) {
        if (customer == null || table == null) {
            throw new IllegalArgumentException("Customer and table are required.");
        }
        table.seatCustomer(customer);
        customer.sitAtTable(table);
        assignedTables.add(table);
    }

    public void takeOrder(Customer customer, Order order) {
        if (customer == null || order == null) {
            throw new IllegalArgumentException("Customer and order are required.");
        }
        customer.placeOrder(order);
        System.out.println("Order " + order.getId() + " recorded");
    }

    public String submitOrder(Order order) {
        updateOrderStatus(order);
        return "Server " + getName() + " submitted order " + order.getId() + " to the kitchen.";
    }

    public String deliverOrder(Order order) {
        finishOrder(order);
        return "Order " + order.getId() + " was delivered to ";
    }

    @Override
    public boolean canHandleOrder(Order order) {
        return order != null && order.getServer() == this && isAvailableForWork();
    }

    @Override
    public void acceptOrder(Order order) {
        if (!canHandleOrder(order)) {
            throw new IllegalArgumentException("Order cannot be accepted by " + getName());
        }
        activeOrder = order;
        assignTask();
    }

    @Override
    public void updateOrderStatus(Order order) {
        if (order == null || order != activeOrder) {
            throw new IllegalArgumentException("Order is not being handled by " + getName());
        }
        order.updateStatus("PLACED");
        System.out.println("Order " + order.getId() + " sent to the kitchen.");
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
}
