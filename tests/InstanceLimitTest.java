package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.exceptions.DuplicateObjectException;
import src.exceptions.InstanceLimitException;
import src.classes.*;

public class InstanceLimitTest {

    @Test 
    public void testInstanceLimitReachedTable() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Table(i);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } catch (Exception e) {
            fail("General exception!");

        }


        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Table(101);
        });
    }

    @Test 
    public void testInstanceLimitReachedServer() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Server("n" + i, "id" + i, "hi");
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } catch (Exception e) {
            fail("General exception!");

        }


        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Server("n101", "id 101", "hi");
        });
    }

    @Test 
    public void testInstanceLimitReachedRestaurant() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Restaurant("R: " + i);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } catch (Exception e) {
            fail("General exception!");

        }

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Restaurant("R: 101");
        });
    }

    @Test 
    public void testInstanceLimitReachedOrderItem() {

        //create MenuItem for OrderItem to use
        MenuItem menuItemObj = null; 
        try {
            menuItemObj = new MenuItem("id", "item", 1, "item description", 1);
        } catch (Exception e) {
            System.out.println("Duplicate, instance limit, or quantity error!");
        }
        final MenuItem finalMenuItem = menuItemObj;

        //create 100 instances
        try {

            for(int i = 1; i < 101; i++) {
                new OrderItem(finalMenuItem, i);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } 

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new OrderItem(finalMenuItem, 102);
        });
    }

    @Test 
    public void testInstanceLimitReachedOrder() {

        //create Customer for Order to use
        Customer customer = null;
        Server server = null; 
        try {
            customer = new Customer("customerName");
            server = new Server("serverName", "id", "greeting");
        } catch (Exception e) {
            System.out.println("Duplicate, instance limit, or quantity error!");
        }
        final Customer customerTestObj = customer;
        final Server serverTestObj = server;

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Order("id" + i, customerTestObj, serverTestObj);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } 

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Order("id" + 101, customerTestObj, serverTestObj);
        });
    }

    @Test 
    public void testInstanceLimitReachedMenuItem() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new MenuItem("id" + i, "name" + i, 1, "description", 1);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } 

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new MenuItem("id" + 101, "name", 1, "description", 1);
        });
    }

    @Test 
    public void testInstanceLimitReachedCustomer() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Customer("name" + i);
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } 

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Customer("name" + 101);
        });
    }

    @Test 
    public void testInstanceLimitReachedCook() {

        //create 100 instances
        try {

            for(int i = 0; i < 100; i++) {
                new Cook("name" + i, "id" + i, "characteristic");
            }

        } catch (InstanceLimitException e) {
            fail("Creating the first 100 shouldn't throw exceptions!");

        } catch (DuplicateObjectException e) {
            fail("Duplicate exception!");

        } 

        //successfully InstanceLimitException at 101th instance
        assertThrows(InstanceLimitException.class, () -> {
            new Cook("name" + 101, "id" + 101, "characteristic");
        });
    }

}