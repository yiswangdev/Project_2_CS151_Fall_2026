package Interface;

//this interface is supposed to represent if an employee
//can handle a certain order, and to also provide
//declaration for handle() so that orders can 'step'
public interface Orderhandler {
    //can execute on a certain order
    boolean canHandle(Order order);
    //returns true if a handler (Employee or Cook) 

    //lets the order step once in its lifetime.
    //the sequential order of the order should be
    //something like this:
    //placed -> prepare -> deliver
    //
    //should return a message stating what happened
    //we can also implement a 'throws' error here
    //if the order cannot be stepped for whatever reason
    String handle(Order order);

    //will return the name of the Cook/Employee/Server
    String getHandlerName();

    //will return the role of the Employee (either
    //Cook or Server)
    String getHandlerRole();
}
