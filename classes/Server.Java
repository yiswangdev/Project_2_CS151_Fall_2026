package classes;

import Interface.Orderhandler;
import java.util.ArrayList;

public class Server extends Employee implements OrderHandler {
    private String greeting;
    private ArrayList<Table> assignedTables;

    public Server(String greeting) {
      this.greeting = greeting;
      this.assignedTables = new ArrayList<>();
    }

    public String greetCustomer(Customer customer) {

    }

    public void seatCustomer(Customer customer, Table table) {
    }

    public void takeOrder(Customer customer, Order order) {

    }

    public String deliverOrder(Order order) {

    }

}
 