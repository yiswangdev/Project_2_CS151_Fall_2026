package classes;

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
abstract class Employee {
    private String name, employeeID;
    private boolean isBusy = false;
    private boolean isOnShift = false;

    Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        isBusy = false;
        isOnShift = true;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    void assignTask() {
        // some other logic
        isBusy = true;
    }

    void completeTask() {
        // finish some task/order
        isBusy = false;
    }

    boolean isAvailableForWork() {
        return !isBusy && isOnShift;
    }

    @Override
    public String toString() {
        return name + " (ID: " + employeeID + ")";
    }
}