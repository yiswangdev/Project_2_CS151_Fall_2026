package Restaurant;

public class Cook extends Employee {

    public Cook(String id, String name) {
        super(id, name);
    }

    public String startPreparing(Order order) {
        checkAssigned(order);
        //order.startPreparing();
        return "Cook: " + getName() + " started order " + /*order's ID*/ ": " /* + describe order ticket */;
    }

    private void checkAssigned(Order order) {
        /*
        if (!equals(order.getCook())) {
            //throw exception when cook assigned is not equal to the order's cook
        }
        */
    }

}
