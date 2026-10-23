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
    String name, employeeID;
    boolean isBusy, isOnShift;

    Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
        isBusy = false;
        isOnShift = true;
    }

    void assignTask(){
        //some other logic
        isBusy = true;
    }

    void completeTask(){
        //finish some task/order
        isBusy = false;
    }

    boolean isAvailableForWork(){
        return !isBusy && isOnShift;
    }

    
}