package src.classes;

import java.util.ArrayList;
import java.util.HashSet;

import src.exceptions.*;
import src.utils.ClassInstanceLimit;
import src.utils.DuplicateInstancesCheck;

//Created By: Eric Wang
public class Restaurant {
    private ArrayList<Table> tables;
    private ArrayList<Employee> employees;
    private ArrayList<MenuItem> menuItems;
    private static final HashSet<Object> instances = new HashSet<>();

    public Restaurant() throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "Restaurant");
        tables = new ArrayList<>();
        employees = new ArrayList<>();
        menuItems = new ArrayList<>();
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "Restaurant");
        instances.add(this);
    }

    @Override 
    public boolean equals(Object other){
        Restaurant otherRestaurant = (Restaurant) other;
        return otherRestaurant.getTables().equals(tables) && otherRestaurant.getEmployees().equals(employees) && otherRestaurant.getMenuItems().equals(menuItems);
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

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

    @Override
    public String toString() {
        return "Restaurant: " + tables.size() + " tables, " + employees.size() + " employees, " + menuItems.size()
                + " menu items";
    }
}
