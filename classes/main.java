package classes;
import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ScannerHelper;

//Created by: Eric Wang
public class main {
    private Restaurant restaurant;
    //private int orderNumber = 101;

    public main() {
        try{
        restaurant = new Restaurant();

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
        } catch (Exception e){
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
            /*if (restaurant.findAvailableTables().size() < restaurant.getTables().size()) {
                System.out.print("Did a customer leave? Enter table number (0 for none): ");
                int leaving = readInt();
                if (leaving >= 1 && leaving <= restaurant.getTables().size()
                        && !restaurant.getTables().get(leaving - 1).isAvailable()) {
                    restaurant.getTables().get(leaving - 1).releaseTable();
                    System.out.println("Table " + leaving + " is now available.");
                }
            }*/
           
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
                if (tableChoice >= 1 && tableChoice <= restaurant.getTables().size()
                        && restaurant.getTables().get(tableChoice - 1).isAvailable()) {
                    table = restaurant.getTables().get(tableChoice - 1);
                } else {
                    System.out.println("Choose an available table number.");
                }
            }
            System.out.print("Enter customer name: ");
            Customer customer;
            try{
                customer = new Customer(ScannerHelper.getInput());
            }catch (IllegalArgumentException e) {
                System.out.println("Duplicate object: " + e.getMessage());
                customer = null;
            } catch (InstanceLimitException e) {
                System.out.println("Instance limit reached: " + e.getMessage());
                customer = null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                customer = null;
            }

            Server server = restaurant.assignServer(table);
            System.out.println("Server " + server.getName() + " approaches the table.");
            System.out.println(server.greetCustomer(customer));
            restaurant.seatCustomer(customer, table);
            System.out.println(customer.getName() + " has been seated at Table " + tableChoice + ".");
            Order order;
            try{
                order = new Order(customer, server);
            }catch (IllegalArgumentException e) {
                System.out.println("Duplicate object: " + e.getMessage());
                order = null;
            } catch (InstanceLimitException e) {
                System.out.println("Instance limit reached: " + e.getMessage());
                order = null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                order = null;
            }
            customer.placeOrder(order);
            server.acceptOrder(order);
            String another = "y";
            while (another.equalsIgnoreCase("y")) {
                displayMenu();
                MenuItem item = null;
                while (item == null) {
                    System.out.print("Choose an item out of the ones above: ");
                    int itemChoice = readInt();
                    if (itemChoice >= 1 && itemChoice <= restaurant.getMenuItems().size()) {
                        item = restaurant.getMenuItems().get(itemChoice - 1);
                    }
                }
                System.out.print("Quantity: ");
                int quantity = readInt();
                if (quantity > 0 && item.canFulfillOrder(quantity)) {
                    try{
                        customer.requestAdditionalItem(item, quantity);
                    }catch (IllegalArgumentException e) {
                        System.out.println("Duplicate object: " + e.getMessage());
                    } catch (InstanceLimitException e) {
                        System.out.println("Instance limit reached: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Added " + quantity + " x " + item.getName() + ".");
                } else {
                    System.out.println("Sorry, we can't make that quantity.");
                }
                System.out.print("Choose another item? (y/n): ");
                another = ScannerHelper.getInput().trim();
            }

            System.out.println("Order #" + order.getId() + " created!");
            System.out.println("Table: " + tableChoice);
            System.out.println("Server: " + server.getName());
            System.out.println(order.getOrderSummary());
            System.out.println(server.submitOrder(order));

            Cook cook = restaurant.assignCook(order);
            cook.prepareOrder(order);
            cook.finishOrder(order);
            System.out.println(server.deliverOrder(order) + "table " + tableChoice + ".");
            System.out.println("Here is your food:");
            System.out.println(order);
            System.out.print("Is the customer leaving? (y/n): ");
            if (ScannerHelper.getInput().trim().equalsIgnoreCase("y")) {
                customer.leaveRestaurant();
            }

            System.out.print("Seat another customer? (y/n): ");
            nextCustomer = ScannerHelper.getInput().trim();
        }
    }

    public static void main(String[] args) {
        new main().run();
    }
}
