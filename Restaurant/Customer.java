package Restaurant;

public class Customer {
    private final String id;
    private final String name;
    private Order currentOrder;
    private int completedOrders;
    private double totalSpent;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

     public void startOrder(Order order) {
         if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
         }
}
