package classes;
import Interface.Orderhandler;

abstract class Employee implements Orderhandler {
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