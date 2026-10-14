package Restaurant;
public class Server extends Employee implements OrderHandler {
    private String specialGreeting;
  public Server(String id, String name) {
    super(id, name);
    specialGreeting = "";
  }
  public Server(String id, String name, String greeting) {
    super(id, name);
    this.specialGreeting = greeting;
  }
  public String getRole() {
    return "Server";
  }
  public String greet(Customer customer) {
    return "Hi " + customer.getName() + ", I'm " + getName() + " and I'll be your server today!";
  }
  public String submitOrder(Order order){
    order.place();
    return "Server " + getName() + " submitted order " + order.getId() + " to the kitchen.";
  }
  public String completeOrder(Order order){
    order.complete();
    return "Server " + getName() + " delivered order " + order.getId() + " to the table.";
  }
}
