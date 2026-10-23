package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

// created by Maria
public class Server extends Employee {
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
}