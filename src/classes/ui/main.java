package src.classes.ui;

import src.classes.*;
import src.exceptions.*;
import src.utils.ScannerHelper;

// Created by: Eric Wang
public class main {
    private Restaurant restaurant;

    public main() {
        try {
            restaurant = new Restaurant("Awesome Resturant");

            for (int i = 1; i <= 4; i++) {
                restaurant.addTable(new Table(i));
            }

            restaurant.addEmployee(new Server("Jamie", "S1", "Hey there! Welcome to our restaurant!"));
            restaurant.addEmployee(new Cook("Emily", "C1", "Spicy"));
            restaurant.addMenuItem(new MenuItem("1", "Burger", 12.99, "Beef patty with cheese", 20));
            restaurant.addMenuItem(new MenuItem("2", "Alfredo Pasta", 14.99, "Fettuccine in cream sauce", 20));
            restaurant.addMenuItem(new MenuItem("3", "Caesar Salad", 8.99, "Romaine with parmesan", 20));
            restaurant.addMenuItem(new MenuItem("4", "Lemonade", 2.99, "Fresh squeezed", 20));
        } catch (DuplicateObjectException e) {
            System.out.println("Duplicate object: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Instance limit reached: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayTables() {
        System.out.println("Available Tables:");
        for (Table table : restaurant.findAvailableTables()) {
            System.out.println(table.getTableInfo());
        }
    }

    public void displayMenu() {
        System.out.println("=== Menu ===");
        for (int i = 0; i < restaurant.getMenuItems().size(); i++) {
            System.out.println((i + 1) + ". " + restaurant.getMenuItems().get(i).formatMenuDisplay());
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(ScannerHelper.getInput().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a number instead: ");
            }
        }
    }

    public void run() {
        String nextCustomer = "y";
        while (nextCustomer.equalsIgnoreCase("y")) {
            if (restaurant.findAvailableTables().isEmpty()) {
                System.out.println("All tables are full.");
                break;
            }

            serveCustomer();

            System.out.print("Seat another customer? (y/n): ");
            nextCustomer = ScannerHelper.getInput().trim();
        }
    }

    private boolean hasAvailableTables() {
        return !restaurant.findAvailableTables().isEmpty();
    }

    private void serveCustomer() {
        displayTables();
        int tableChoice = chooseAvailableTable();
        Table table = restaurant.getTables().get(tableChoice - 1);
        Customer customer = createCustomer();
        Server server = restaurant.assignServer(table);
        System.out.println("Server " + server.getName() + " approaches the table.");
        System.out.println(server.greetCustomer(customer));
        restaurant.seatCustomer(customer, table);
        System.out.println(customer.getName() + " has been seated at Table " + tableChoice + ".");
        Order order = createOrder(customer, server);
        customer.placeOrder(order);
        server.acceptOrder(order);
        takeOrderItems(customer);
        submitOrder(order, server, tableChoice);
        prepareAndDeliverOrder(order, server, tableChoice);
        askCustomerToLeave(customer);
    }

    private int chooseAvailableTable() {
        while (true) {
            System.out.print("Choose a table: ");
            int tableChoice = readInt();
            if (tableChoice >= 1 && tableChoice <= restaurant.getTables().size()
                    && restaurant.getTables().get(tableChoice - 1).isAvailable()) {
                return tableChoice;
            }
            System.out.println("Choose an available table number.");
        }
    }

    private Customer createCustomer() {
        System.out.print("Enter customer name: ");
        try {
            return new Customer(ScannerHelper.getInput());
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate object: " + e.getMessage());
        } catch (InstanceLimitException e) {
            System.out.println("Instance limit reached: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Order createOrder(Customer customer, Server server) {
        try {
            return new Order(customer, server);
        } catch (DuplicateObjectException e) {
            System.out.println("Duplicate object: " + e.getMessage());
        } catch (InstanceLimitException e) {
            System.out.println("Instance limit reached: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void takeOrderItems(Customer customer) {
        String another = "y";
        while (another.equalsIgnoreCase("y")) {
            displayMenu();
            MenuItem item = chooseMenuItem();
            System.out.print("Quantity: ");
            int quantity = readInt();
            addItemToOrder(customer, item, quantity);
            System.out.print("Choose another item? (y/n): ");
            another = ScannerHelper.getInput().trim();
        }
    }

    private MenuItem chooseMenuItem() {
        while (true) {
            System.out.print("Choose an item out of the ones above: ");
            int itemChoice = readInt();
            if (itemChoice >= 1 && itemChoice <= restaurant.getMenuItems().size()) {
                return restaurant.getMenuItems().get(itemChoice - 1);
            }
        }
    }

    private void addItemToOrder(Customer customer, MenuItem item, int quantity) {

        try {
            if (quantity <= 0 && !item.canFulfillOrder(quantity)) {
                throw new QuantityUnavailableException("Sorry, we can't make that quantity.");
            }

            customer.requestAdditionalItem(item, quantity);
            System.out.println("Added " + quantity + " x " + item.getName() + ".");

        } catch (DuplicateObjectException e) {
            System.out.println("Duplicate object: " + e.getMessage());

        } catch (InstanceLimitException e) {
            System.out.println("Instance limit reached: " + e.getMessage());

        } catch (QuantityUnavailableException e) {
            System.out.println(e.getMessage());
        }
            
    }

    private void submitOrder(Order order, Server server, int tableChoice) {
        System.out.println("\n------------------");
        System.out.println("Receipt\n");
        System.out.println("Order #" + order.getId() + " created!");
        System.out.println("Table: " + tableChoice);
        System.out.println("Server: " + server.getName());
        System.out.println(order.getOrderSummary());
        System.out.println("(" + server.submitOrder(order) + ")");
        System.out.println("------------------\n");

    }

    private void prepareAndDeliverOrder(Order order, Server server, int tableChoice) {
        Cook cook = restaurant.assignCook(order);
        cook.prepareOrder(order);
        cook.finishOrder(order);
        System.out.println(server.deliverOrder(order) + "table " + tableChoice + ".\n");
        System.out.println("Here is your food:");
        System.out.println(order);
    }

    private void askCustomerToLeave(Customer customer) {
        System.out.print("Is the customer leaving? (y/n): ");
        if (ScannerHelper.getInput().trim().equalsIgnoreCase("y")) {
            customer.leaveRestaurant();
        }
    }

    public static void main(String[] args) {
        new main().run();
    }
}
