package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

public class Server extends Employee implements OrderHandler {
  private String greeting;
  private ArrayList<Table> assignedTables;

  public Server(String name, String employeeID, String greeting) {
    super(name, employeeID);
    this.greeting = greeting;
    this.assignedTables = new ArrayList<>();
  }

  public String greetCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null.");
    }
    return greeting + ", " + customer.getName() + "! I'm " + name;
  }

  public void seatCustomer(Customer customer, Table table) {
    table.seatCustomer(customer);
    customer.sitAtTable(table);
    assignedTables.add(table);
    System.out.println(customer.getName() + " has been seated at Table " + table.getTableNumber())
    
  }

  public void takeOrder(Customer customer, Order order) {
    customer.placeOrder(order);
    System.out.println("Order " + order.getId() + " recorded for " + customer.getName());
  }

  public String deliverOrder(Order order) {
    if (order == null) {
      throw new IllegalArgumentException("Order cannot be null");
    }
    return "Order " + order.getId() + " is ready!";
  }

  @Override
  public boolean canHandleOrder(Order order) {
    return order != null && isAvailableForWork();
  }

  @Override
  public void acceptOrder(Order order) {
    if (!canHandleOrder(order)) {
      throw new IllegalArgumentException("This order is not assigned to " + name);
    }
    assignTask();
  }

  @Override
  public void updateOrderStatus(Order order) {
    if (!canHandleOrder(order)) {
      throw new IllegalArgumentException("This order is not assigned to " + name);
    }
    order.place();
  }

  @Override
  public void finishOrder(Order order) {
  if (!canHandleOrder(order)) {
    throw new IllegalArgumentException("This order is not assigned to " + name);
  }
  completeTask();
  }
}