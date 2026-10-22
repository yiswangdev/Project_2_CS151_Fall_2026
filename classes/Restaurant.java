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
        for (Employee employee : employees) {
            if (employee instanceof Server server && employee.isAvailableForWork()) {
                return server;
            }
        }
        return null; // No available server
    }

    public Cook assignCook(Order order) {
        for (Object employee : employees) {
            if (employee instanceof Cook cook && cook.isAvailableForWork()) {
                return cook;
            }
        }
        return null; // No available cook
    }

    public void seatCustomer(Customer customer, Table table) {
        if (table != null && table.isAvailable()) {
            table.seatCustomer(customer);
            customer.sitAtTable(table);
        }
    }

}
