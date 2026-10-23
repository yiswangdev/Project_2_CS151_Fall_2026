package classes;

// created by Maria
public class Table {
    private final int tableNumber;
    private boolean isOccupied;
    private Customer customer;
 
    /**
     * Creates an empty, unoccupied table.
     *
     * @param tableNumber the number identifying this table
     */
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.customer = null;
    }

    /**
     * Seats a customer if the table is available.
     *
     * @param customer the customer to seat; must not be null
     * @throws IllegalArgumentException if the customer is null
     * @throws IllegalStateException if the table is already occupied
     */
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

    /**
     * Removes the customer and marks the table as unoccupied.
     *
     * @throws IllegalStateException if the table is already empty
     */
    public void releaseTable() {
        if (isAvailable()) {
            throw new IllegalStateException("Table " + tableNumber + " is already empty.");
        }
        this.customer = null;
        this.isOccupied = false;
    }

/**
     * Checks whether the table can seat a new customer.
     *
     * @return {@code true} if the table is unoccupied
     */
    public boolean isAvailable() {
        return !isOccupied;
    }

    /**
     * Returns the table number and occupancy information for display.
     *
     * @return a string such as "Table 3: Available" or "Table 3: Occupied by ..."
     */
    public String getTableInfo() {
        if (isAvailable()) {
            return "Table " + tableNumber + ": Occupied by " + customer;
        }
        return "";
    }
}