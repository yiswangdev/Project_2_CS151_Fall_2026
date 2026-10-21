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
}
