package classes;

import java.util.ArrayList;

//Created By: Eric Wang
public class Restaurant {
    ArrayList<Table> tables = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    public ArrayList<Table> findAvailableTables() {
        ArrayList<Table> availableTables = new ArrayList<>();
        for (Table table : tables) {
            if (table.isAvailable()) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    public Server assignServer(Table table) {
        if (table == null || !table.isAvailable()) {
            throw new IllegalArgumentException("An available table is required.");
        }
        for (Employee employee : employees) {
            if (employee instanceof Server server && employee.isAvailableForWork()) {
                return server;
            }
        }
        throw new IllegalStateException("No server is available.");
    }

    public Cook assignCook(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        for (Employee employee : employees) {
            if (employee instanceof Cook cook && cook.isAvailableForWork()) {
                return cook;
            }
        }
        throw new IllegalStateException("No cook is available.");
    }

    public void seatCustomer(Customer customer, Table table) {
        if (customer == null || table == null) {
            throw new IllegalArgumentException("Customer and table are required.");
        }
        table.seatCustomer(customer);
        customer.sitAtTable(table);
    }

}
