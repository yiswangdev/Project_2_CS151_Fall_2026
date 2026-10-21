package classes;

public abstract class Employee {
  private final String id;
  private final String name;
  private boolean onShift;
  private int ordersHandled;

  public Employee(String id, String name) {
    this.id = id;
    this.name = name;
    this.onShift = false;
    this.ordersHandled = 0;
  }

  // Getters and setters for the fields
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isOnShift() {
    return onShift;
  }

  public void setOnShift(boolean onShift) {
    if (this.onShift && !onShift) {
      // If the employee is being clocked out, reset orders handled
      this.ordersHandled = 0;
    }
    this.onShift = onShift;
  }

  public int getOrdersHandled() {
    if (!onShift) {
      throw new IllegalStateException(name + " is not on shift.");
    }
    return ordersHandled;
  }

  public void setOrdersHandled(int ordersHandled) {
    if (!onShift) {
      throw new IllegalStateException(name + " is not on shift.");
    }
    if (ordersHandled < 0) {
      throw new IllegalArgumentException("Orders handled cannot be negative.");
    }
    this.ordersHandled = ordersHandled;
  }

  public void startShift() {
    if (onShift) {
      throw new IllegalStateException(name + " is already on shift.");
    }
    onShift = true;
  }

  // Clocks the employee out
  public void endShift() {
    if (!onShift) {
      throw new IllegalStateException(name + " is not on shift.");
    }
    onShift = false;
  }

  // Only employees on shift can be randomly assigned to orders
  public boolean canTakeOrders() {
    if (!onShift) {
      throw new IllegalStateException(name + " is not on shift.");
    }
    return onShift;
  }

  // Called each time the employee moves an order forward
  public void recordHandledOrder() {
    if (!onShift) {
      throw new IllegalStateException(name + " is not on shift.");
    }
    ordersHandled++;
  }
  // Equality based on the employee's unique ID
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    return other instanceof Employee && id.equals(((Employee) other).id);
  }
  // Hash code based on the employee's unique ID
  @Override
  public int hashCode() {
    if (id == null) {
      return 0;
    }
    return id.hashCode();
  }

  // String representation of the employee, including ID, name, shift status, and orders handled
  @Override
  public String toString() {
    return "[" + id + "] " + name + " - " + (onShift ? "on shift" : "off shift")
        + ", " + ordersHandled + " order(s) handled";
  }
  
}

