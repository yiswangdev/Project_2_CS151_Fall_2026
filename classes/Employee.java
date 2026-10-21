package classes;
import Interface.Orderhandler;

abstract class Employee implements Orderhandler {
    String name, employeeID;
    Boolean isBusy, isOnShift;

    Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }

    void assignTask(){
        //some other logic
        isBusy = true;
    }

    void completeTask(){
        //finish some task/order
        isBusy = false;
    }

    Boolean isAvailableForWork(){
        if (!isBusy && isOnShift) {
            return true;
        } else {
            return false;
        }

    }

    
}