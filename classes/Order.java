package classes;

import java.util.ArrayList;

public class Order {
    public static final int MAX_QUANTITY_PER_ITEM = 20;
    private String id;
    private Customer customer;
    private Server server;
    private ArrayList<OrderItem> items;
    private Cook cook;
    private String note;
    private String status = "DRAFT";

    public Order(String id, Customer customer, Server server) {
        this.id = id;
        this.customer = customer;
        this.server = server;
        this.note = "";
        items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Server getServer() {
        return server;
    }

    public Cook getCook() {
        return cook;
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }

    public void assignCook(Cook cook) {
        this.cook = cook;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculateSubtotal();
        }
        return total;
    }

    public String getOrderSummary() {
        String summary = "";
        for (OrderItem item : items) {
            summary += item + "\n";
        }
        return summary + "Total: $" + String.format("%.2f", calculateTotal());
    }

    @Override
    public String toString() {
        return "Order " + id + " [" + status + "] - " + items.size() + " item(s), Total: $" + String.format("%.2f", calculateTotal());
    }
}