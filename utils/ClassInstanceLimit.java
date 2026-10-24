package utils;

public class ClassInstanceLimit {
    private static final int MAXIMUM_INSTANCES = 100;

    private static int resturantCount = 0;
    private static int employeeCount = 0;
    private static int customerCount = 0;
    private static int menuItemCount = 0;
    private static int orderCount = 0;
    private static int orderItemCount = 0;
    private static int tableCount = 0;

    public static boolean canCreateResturant() {
        if (resturantCount >= MAXIMUM_INSTANCES) {
            // throw exception
        }
        resturantCount++;
        return true;
    }

}
