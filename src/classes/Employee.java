package src.classes;

/**
 * Represents an Employee inside of a resturant.
 * 
 * This class provides methods to set if an Employee
 * is busy or not based on if they were assigned a task,
 * and to also check if an Employee is busy.
 * 
 * Inherited classes include Cook, Server, and for
 * possible future implementation Janitor, Busser,
 * Bartender, etc.
 */

// Created by: Isaac
abstract class Employee {
    private String name, employeeID;
    private boolean isBusy = false;
    private boolean isOnShift = false;

    protected Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        isBusy = false;
        isOnShift = true;
    }

    @Override 
    public boolean equals(Object other){
        Employee otherEmployee = (Employee) other;
        return otherEmployee.getName().equals(name) && otherEmployee.getEmployeeID().equals(employeeID);
    }

    public String getName() {
        return name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    protected void assignTask() {
        isBusy = true;
    }

    protected void completeTask() {
        isBusy = false;
    }

    protected boolean isAvailableForWork() {
        return !isBusy && isOnShift;
    }

    @Override
    public String toString() {
        return name + " (ID: " + employeeID + ")";
    }
}