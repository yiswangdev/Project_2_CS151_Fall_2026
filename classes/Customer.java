package classes;

//Created by: Eric Wang
public class Customer {
    String name;
    Table table;
    Order order;

    public void requestAdditionalItem(MenuItem item, int quantity) {
        if (order == null) {
            System.out.println("Place an order before requesting another item.");
        } else {
           order.addItem(new OrderItem(item, quantity));
        }
    }

    public void leaveRestaurant() {
        if (table != null) {
            table.releaseTable();
        }
        table = null;
        order = null;
    }
}
