package Restaurant;

import java.util.ArrayList;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private ArrayList<String> characteristics;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.characteristics = new ArrayList<>();
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
