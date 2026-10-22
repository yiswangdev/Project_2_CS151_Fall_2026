package classes;

import java.util.Scanner;

//Created by: Eric Wang
public class main {
    private Restaurant restaurant;
    private Scanner scanner;
    private int orderNumber = 101;
    public main() {
        restaurant = new Restaurant();
        scanner = new Scanner(System.in);
        for (int i = 1; i <= 4; i++) {
            restaurant.tables.add(new Table(i));
        }
        restaurant.employees.add(new Server("Jamie", "S1", "Hey there! Welcome to our restaurant!"));
        restaurant.employees.add(new Cook("Emily", "C1"));
        restaurant.menuItems.add(new MenuItem("1", "Burger", 12.99, "Beef patty with cheese", 20));
        restaurant.menuItems.add(new MenuItem("2", "Alfredo Pasta", 14.99, "Fettuccine in cream sauce", 20));
        restaurant.menuItems.add(new MenuItem("3", "Caesar Salad", 8.99, "Romaine with parmesan", 20));
        restaurant.menuItems.add(new MenuItem("4", "Lemonade", 2.99, "Fresh squeezed", 20));
    }

    public void displayTables() {
        System.out.println("Available Tables:");
        for (Table table : restaurant.findAvailableTables()) {
            System.out.println(table.getTableInfo());
        }
    }

    public void displayMenu() {
        System.out.println("=== Menu ===");
        for (int i = 0; i < restaurant.menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + restaurant.menuItems.get(i).formatMenuDisplay());
        }
    }
}
