package Interface;

import classes.Order;

public interface Orderhandler {


    /**
     * Check if an employee can take the responsibility of the order
     * 
     * @param order 
     * @return T/F
     */
    boolean canHandleOrder(Order order);


    /** 
     * Assign order to an employee
     * 
     * @param order
     */ 
    void acceptOrder(Order order);


    /**
     * Update the order's progress,
     * placed -> preparing
     * 
     * @param order
     */
    void updateOrderStatus(Order order);


    /**
     * Complete the employee's part of the order's workflow
     * 
     * @param order
     */
    void finishOrder(Order order);
    
}
