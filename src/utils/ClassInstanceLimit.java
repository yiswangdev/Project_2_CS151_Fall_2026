package src.utils;
import java.util.*;
import src.exceptions.InstanceLimitException;

public class ClassInstanceLimit {

    private static final int MAXIMUM_INSTANCES = 100;
    /*static int resturantCount = 0;
    private static int employeeCount = 0;
    private static int customerCount = 0;
    private static int menuItemCount = 0;
    private static int orderCount = 0;
    private static int orderItemCount = 0;
    private static int tableCount = 0;
    private static String exceptionMessage = "Cannot make more than 100 instances of: ";*/

    public static void checkLimit(HashSet<Object> instances, String className) throws InstanceLimitException {
        if (instances.size() >= MAXIMUM_INSTANCES) {
            throw new InstanceLimitException("Cannot create more than " + MAXIMUM_INSTANCES + " instances of " + className);
        }
        /*switch (className) {

            case "Resturant":
                if (resturantCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                resturantCount++;
                break;

            case "Employee", "Server", "Cook":
                if (employeeCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                employeeCount++;
                break;

            case "Customer":
                if (customerCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                customerCount++;
                break;

            case "MenuItem":
                if (menuItemCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                menuItemCount++;
                break;

            case "Order":
                if (orderCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                orderCount++;
                break;

            case "OrderItem":
                if (orderItemCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                orderItemCount++;
                break;

            case "Table":
                if (tableCount >= MAXIMUM_INSTANCES) {
                    throw new InstanceLimitException(exceptionMessage + className);
                }
                tableCount++;
                break;

            default:
                throw new IllegalArgumentException(className + " does not exist!");
        }*/
    }
}