package classes;

public class Customer {
    // Missing: a constructor or setter to initialize the customer's name.
    String name;
    Table table;
    Order order;

    public void sitAtTable(Table table) {
        this.table = table;
    }

    public void placeOrder(Order order) {
        this.order = order;
    }

    public void requestAdditionalItem(MenuItem item, int quantity) {
        if (order == null) {
            System.out.println("Place an order before requesting another item.");
        } else {
            // Missing: Order needs an item-adding method; item and quantity are not used yet.
            System.out.println("Additional items cannot be added until Order supports them.");
        }
    }

    public void leaveRestaurant() {
        // Missing: release the table through Table; this only clears this customer's references.
        table = null;
        order = null;
    }
}
