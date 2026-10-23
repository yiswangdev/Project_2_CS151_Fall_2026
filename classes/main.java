package classes;

import java.util.Scanner;

//Created by: Eric Wang
public class main {
    private Restaurant restaurant;
    private Scanner scanner;
    private int orderNumber = 101;

    private static final int MAXIMUM_INSTANCES = 100;

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
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }
    public void run() {
        String nextCustomer = "y";
        while (nextCustomer.equalsIgnoreCase("y")) {
             if (restaurant.findAvailableTables().size() < restaurant.tables.size()) {
                System.out.print("Did a customer leave? Enter table number (0 for none): ");
                int leaving = readInt();
                if (leaving >= 1 && leaving <= restaurant.tables.size()
                        && !restaurant.tables.get(leaving - 1).isAvailable()) {
                    restaurant.tables.get(leaving - 1).releaseTable();
                    System.out.println("Table " + leaving + " is now available.");
                }
            }
            if (restaurant.findAvailableTables().isEmpty()) {
                System.out.println("All tables are full.");
                break;
                }        
            displayTables();
            Table table = null;
            int tableChoice = 0;
            while (table == null) {
                System.out.print("Choose a table: ");
                tableChoice = readInt();
                if (tableChoice >= 1 && tableChoice <= restaurant.tables.size()
                        && restaurant.tables.get(tableChoice - 1).isAvailable()) {
                    table = restaurant.tables.get(tableChoice - 1);
                } else {
                    System.out.println("Choose an available table number.");
                }
            }
            System.out.print("Enter customer name: ");
            Customer customer = new Customer(scanner.nextLine());

            Server server = restaurant.assignServer(table);
            System.out.println("Server " + server.name + " approaches the table.");
            System.out.println(server.greetCustomer(customer));
            restaurant.seatCustomer(customer, table);
            System.out.println(customer.name + " has been seated at Table " + tableChoice + ".");

            Order order = new Order(String.valueOf(orderNumber++), customer, server);
            customer.placeOrder(order);
            server.acceptOrder(order);
            String another = "y";
            while (another.equalsIgnoreCase("y")) {
                displayMenu();
                MenuItem item = null;
                while (item == null) {
                    System.out.print("Choose an item: ");
                    int itemChoice = readInt();
                    if (itemChoice >= 1 && itemChoice <= restaurant.menuItems.size()) {
                        item = restaurant.menuItems.get(itemChoice - 1);
                    }
                }
                System.out.print("Quantity: ");
                int quantity = readInt();
                if (quantity > 0 && item.canFulfillOrder(quantity)) {
                    customer.requestAdditionalItem(item, quantity);
                    System.out.println("Added " + quantity + " x " + item.getName() + ".");
                } else {
                    System.out.println("Sorry, we can't make that quantity.");
                }
                System.out.print("Choose another item? (y/n): ");
                another = scanner.nextLine().trim();
            }

            System.out.println("Order #" + order.getId() + " created!");
            System.out.println("Table: " + tableChoice);
            System.out.println("Server: " + server.name);
            System.out.println(order.getOrderSummary());
            System.out.println(server.submitOrder(order));

            Cook cook = restaurant.assignCook(order);
            cook.prepareOrder(order);
            cook.finishPreparation(order);
            System.out.println(server.deliverOrder(order));
            System.out.print("Is the customer leaving? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                customer.leaveRestaurant();
            }

            System.out.print("Seat another customer? (y/n): ");
            nextCustomer = scanner.nextLine().trim();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new main().run();
    }
}
