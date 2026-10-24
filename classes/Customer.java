package classes;

//Created by: Eric Wang
public class Customer {
    
    private String name;
    private Table table;
    private Order order;
    private static int customerCounter = 0;

    Customer(String name) {
        this.name = name;
        table = null;
        order = null;
        customerCounter++;
    }

    public String getName() {
        return name;
    }

    public void sitAtTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("Table cannot be null.");
        }
        this.table = table;
    }

    public void placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        this.order = order;
    }

    public void requestAdditionalItem(MenuItem item, int quantity) {
        if (order == null) {
            System.out.println("Place an order before requesting another item.");
            return;
        }
        if (item == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }
        item.fulfillOrder(quantity);
        order.addItem(new OrderItem(item, quantity));
    }

    public void leaveRestaurant() {
        if (table != null) {
            table.releaseTable();
        }
        table = null;
        order = null;
    }

    @Override
    public String toString() {
        return name;
    }
}
