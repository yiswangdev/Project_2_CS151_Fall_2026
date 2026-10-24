package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;

//Created by: Eric Wang
public class Customer {
    
    private String name;
    private Table table;
    private Order order;
    //private static int customerCounter = 0;
    private static final Set<Customer> instances = new HashSet<>();

    Customer(String name) throws Exception{
        this.name = name;
        table = null;
        order = null;
        //customerCounter++;
        for (Customer customer : instances) {
            if (customer.getName().equals(name) && customer.table == table && customer.order == null) {
                throw new DuplicateObjectException("Duplicate customer: " + name);
            }
        }
        instances.add(this);
        if(instances.size() > 100){
            throw new InstanceLimitException("Too many customers created! - " + instances.size());
        }
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
        order.setCustomer(this);
    }

    public void requestAdditionalItem(MenuItem item, int quantity) throws Exception{
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
