package src.classes;

import java.util.HashSet;

import src.exceptions.DuplicateObjectException;
import src.exceptions.InstanceLimitException;
import src.utils.ClassInstanceLimit;
import src.utils.DuplicateInstancesCheck;

// Created by: Maria
public class Table {
    private final int tableNumber;
    private boolean isOccupied;
    private Customer customer = null;
    private static final HashSet<Object> instances = new HashSet<>();
 
    /**
     * Creates an empty, unoccupied table.
     *
     * @param tableNumber the number identifying this table
     */
    public Table(int tableNumber) throws DuplicateObjectException, InstanceLimitException{
        ClassInstanceLimit.checkLimit(instances, "Table");
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        DuplicateInstancesCheck.checkForDuplicate(instances, (Object)this, "Table");
        instances.add(this);
    }

    @Override
    public boolean equals(Object other){
        Table otherTable = (Table) other;
        return otherTable.tableNumber == tableNumber && otherTable.isOccupied == isOccupied
                && otherTable.customer == customer;
    }

    public void seatCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (!isAvailable()) {
            throw new IllegalStateException("Table " + tableNumber + " is already occupied");
        }
        this.customer = customer;
        this.isOccupied = true;
    }

    public void releaseTable() {
        if (isAvailable()) {
            throw new IllegalStateException("Table " + tableNumber + " is already empty.");
        }
        this.customer = null;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public String getTableInfo() {
        if (isAvailable()) {
            return "Table " + tableNumber + ": Available";
        }
        return "Table " + tableNumber + ": Occupied by " + customer.getName();
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString() {
        return "Table " + tableNumber + (isOccupied ? " (Occupied)" : " (Available)");
    }
}