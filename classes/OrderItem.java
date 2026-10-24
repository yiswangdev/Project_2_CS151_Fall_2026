package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private ArrayList<String> characteristics;
    //private static int orderItemCounter = 0;
    private static final Set<OrderItem> instances = new HashSet<>();

    public OrderItem(MenuItem menuItem, int quantity) throws Exception{
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.characteristics = new ArrayList<>();
        //orderItemCounter++;
        for (OrderItem orderItem : instances) {
            if (orderItem.menuItem.equals(menuItem) && orderItem.quantity == quantity) {
                throw new DuplicateObjectException("Duplicate order: " + quantity + " " + menuItem);
            }
        }
        instances.add(this);
        if(instances.size() > 100){
            throw new InstanceLimitException("Too many order items created! - " + instances.size());
        }
    }

    public void addCharacteristic(String characteristic) {
        if (characteristic != null && !characteristic.isBlank() && !characteristics.contains(characteristic)) {
            characteristics.add(characteristic);
        }
    }

    public void removeCharacteristic(String characteristic) {
        characteristics.remove(characteristic);
    }

    public double calculateSubtotal() {
        return menuItem.priceFor(quantity);
    }

    public String getDescription() {
        String description = quantity + "x ";

        for (String characteristic : characteristics) {
            description += characteristic + " ";
        }

        return description + menuItem.getName();
    }

    @Override
    public String toString() {
        return getDescription() + " - $" 
                + String.format("%.2f", calculateSubtotal());
    }
}
