package src.Interface;
import src.classes.Order;

// Created by: Isaac
public interface Orderhandler {

    boolean canHandleOrder(Order order);

    void acceptOrder(Order order);

    void updateOrderStatus(Order order);

    void finishOrder(Order order);

}