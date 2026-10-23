package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

// created by Maria
public class Server extends Employee implements OrderHandler {
  private String greeting;
  private ArrayList<Table> assignedTables;

  public Server(String name, String employeeID, String greeting) {
    super(name, employeeID);
    this.greeting = greeting;
    this.assignedTables = new ArrayList<>();
  }

  /**
   * Greets the customer and introduces this server.
   *
   * @param customer the customer to greet
   * @return the greeting with the server's name
   * @throws IllegalArgumentException if the customer is null
   */
  public String greetCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null.");
    }
    return greeting + " I'm " + name;
  }

  /**
   * Seats the customer at the table and assigns the table to this server.
   *
   * @param customer the customer to seat
   * @param table the table to seat them at
   * @throws IllegalStateException if the table is occupied
   */
  public void seatCustomer(Customer customer, Table table) {
    table.seatCustomer(customer);
    customer.sitAtTable(table);
    assignedTables.add(table);
    System.out.println("customer has been seated at Table " + table.getTableInfo());
    
  }

   /**
   * Records the customer's order and prints a confirmation.
   *
   * @param customer the customer ordering
   * @param order the order to record
   */
  public void takeOrder(Customer customer, Order order) {
    customer.placeOrder(order);
    System.out.println("Order " + order.getId() + " recorded");
  }

  /**
   * Delivers the order to the customer.
   *
   * @param order the order to deliver
   * @return a message that the order is ready
   * @throws IllegalArgumentException if the order is null
   */
  public String deliverOrder(Order order) {
    if (order == null) {
      throw new IllegalArgumentException("Order cannot be null");
    }
    return "Order " + order.getId() + " is ready!";
  }

  /**
   * Checks whether this server can take the order.
   *
   * @param order the order to check
   * @return {@code true} if the order is not null and the server is available
   */
  @Override
  public boolean canHandleOrder(Order order) {
    return order != null && isAvailableForWork();
  }

  /**
   * Accepts the order and marks this server as busy.
   *
   * @param order the order to accept
   * @throws IllegalArgumentException if this server cannot handle the order
   */
  @Override
  public void acceptOrder(Order order) {
    if (!canHandleOrder(order)) {
      throw new IllegalArgumentException("This order is not assigned to " + name);
    }
    assignTask();
  }


  
   /**
   * Sends the order to the kitchen.
   *
   * @param order the order to send
   * @throws IllegalArgumentException if this server cannot handle the order
   */
  @Override
  public void updateOrderStatus(Order order) {
    if (!canHandleOrder(order)) {
      throw new IllegalArgumentException("This order is not assigned to " + name);
    }
    System.out.println("Order " + order.getId() + " sent to the kitchen.");
  }

   /**
   * Finishes this server's part of the order and frees the server.
   *
   * @param order the completed order
   * @throws IllegalArgumentException if this server cannot handle the order
   */
  @Override
  public void finishOrder(Order order) {
  if (!canHandleOrder(order)) {
    throw new IllegalArgumentException("This order is not assigned to " + name);
  }
  completeTask();
  }
}