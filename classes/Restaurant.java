package classes;

import java.util.ArrayList;

public class Restaurant {
    ArrayList<Table> tables = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    public Table findAvailableTable() {
        for (Table table : tables) {
            if (table.isAvailable()) {
                return table;
            }
        }
        return null; // No available tables
    }

    public void assignServer(Table table) {
        for (Employee employee : employees) {
            if (employee instanceof Server && employee.canTakeOrders()) {
                // Assign the server to the table
                break;
            }
        }
    }

    public void assignCook(Order order) {
        for (Object employee : employees) {
            if (employee instanceof Cook cook && cook.canTakeOrders()) {
                // Assign the cook to the table
                break;
            }
        }
    }

    public void seatCustomer(Customer customer, Table table) {
        if (table != null && table.isAvailable()) {
            table.setCustomer(customer);
            table.setAvailable(false);
        }
    }

}
