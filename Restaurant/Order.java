package Restaurant;
import java.util.*;
public class Order {
    public static final int MAX_QUANTITY_PER_ITEM = 20;
    private String id;
    private Customer customer;
    private Server server;
    private Map<MenuItem, Integer> items;
    private Cook cook;
    private String note
    private OrderStatus status = OrderStatus.DRAFT;
    public Order(String id, Customer customer, Server server) {
        this.id = id;
        this.customer = customer;
        this.server = server;
        this.note = "";
        items = new HashMap();
    }
    public String getId() { return id; }
    public OrderStatus getStatus() {
        return status; 
    }
    public Server getServer() {
        return server; 
    }
    public Cook getCook() {
        return cook; 
    }
}
