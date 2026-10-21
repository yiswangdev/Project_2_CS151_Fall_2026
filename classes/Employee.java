package classes;
import Interface.Orderhandler;

abstract class Employee implements Orderhandler {
    String name;
    String employeeID;
    Boolean isBusy;

    void assignTask(){}

    void completeTask(){}

    Boolean isAvailableForWork(){
      return isBusy;
    }

    
}