package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.utils.DuplicateInstancesCheck;
import src.exceptions.DuplicateObjectException;
import src.exceptions.InstanceLimitException;
import src.classes.*;


public class DuplicateObjectTest {

    @Test 
    public void testDuplicateInstanceTable() {


        //create initial instance
        try {
            new Table(1);
        } catch (Exception e) {
            fail("First table shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new Table(1);
        });

    }
    
    @Test 
    public void testDuplicateInstanceServer() {


        //create initial instance
        try {
            new Server("bob", "2", "hi");
        } catch (Exception e) {
            fail("First server shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new Server("bob", "2", "hi");
        });

    }

    @Test 
    public void testDuplicateInstanceRestaurant() {


        //create initial instance
        try {
            new Restaurant("brand");
        } catch (Exception e) {
            fail("First resturant shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new Restaurant("brand");
        });

    }

    @Test 
    public void testDuplicateInstanceOrder() {

        //create customer
        Customer customer = null;
        Server server = null;
        try {
            customer = new Customer("poop");
            server = new Server("poop", "poop", "poop");
        } catch (Exception e) {
            System.out.println("Duplicate, instance limit, or quantity error!");
        }
        final Customer customerFinal = customer;
        final Server serverFinal = server;

        //create initial instance
        try {
            new Order("1", customerFinal, serverFinal);
        } catch (Exception e) {
            fail("First Order shouldn't throw an error");
        }

        //throw exception only when Order is somehow duplicate
        try {
            new Order("1", customerFinal, serverFinal);
        } catch (DuplicateObjectException e) {
            fail("Order has incrementing ids, so it shouldn't throw an exception");
        }

    }

    @Test 
    public void testDuplicateInstanceMenuItem() {


        //create initial instance
        try {
            new MenuItem("1", "item", 1, "desc", 1);
        } catch (Exception e) {
            fail("First MenuItem shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new MenuItem("1", "item", 1, "desc", 1);
        });

    }

    @Test 
    public void testDuplicateInstanceCustomer() {


        //create initial instance
        try {
            new Customer("poop");
        } catch (Exception e) {
            fail("First customer shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new Customer("poop");
        });

    }


    @Test 
    public void testDuplicateInstanceCook() {


        //create initial instance
        try {
            new Cook("bob", "1", "none");
        } catch (Exception e) {
            fail("First cook shouldn't throw an error");
        }

        //successfully throw DuplicateObjectException when objects are same
        assertThrows(DuplicateObjectException.class, () -> {
            new Cook("bob", "1", "none");
        });

    }
}
