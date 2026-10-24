package classes;

import java.util.HashSet;
import java.util.Set;

import exceptions.DuplicateObjectException;
import exceptions.InstanceLimitException;
import utils.ClassInstanceLimit;

// Created by: Maria
public class Table {
    private final int tableNumber;
    private boolean isOccupied;
    private Customer customer;
    private static final Set<Table> instances = new HashSet<>();

    public Table(int tableNumber) throws DuplicateObjectException, InstanceLimitException {
        ClassInstanceLimit.checkLimit("Cook");
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.customer = null;
        for (Table table : instances) {
            if (table.tableNumber == tableNumber) {
                throw new DuplicateObjectException("Duplicate Table - " + tableNumber);
            }
        }
        instances.add(this);
        if (instances.size() > 100) {
            throw new InstanceLimitException("Too many order items created! - " + instances.size());
        }
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