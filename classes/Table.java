package classes;

//created by Maria
public class Table {
    private final int tableNumber;
    private boolean isOccupied;
    private Customer customer;

    public Table(int tableNumber) {
        ClassInstanceLimit.checkLimit("Cook");
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.customer = null;
        tableCounter++;
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

    @Override
    public String toString() {
        return "Table " + tableNumber + (isOccupied ? " (Occupied)" : " (Available)");
    }
}