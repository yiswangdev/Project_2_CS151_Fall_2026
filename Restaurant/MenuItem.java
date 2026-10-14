package Restaurant;

public class MenuItem {
    private final String id;
    private final String name;
    private double price;
    private boolean available = true;

    public  MenuItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    