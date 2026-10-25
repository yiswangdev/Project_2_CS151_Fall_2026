package src.utils;
import java.util.*;
import src.exceptions.InstanceLimitException;

public class ClassInstanceLimit {

    private static final int MAXIMUM_INSTANCES = 100;
    public static void checkLimit(HashSet<Object> instances, String className) throws InstanceLimitException {
        if (instances.size() >= MAXIMUM_INSTANCES) {
            throw new InstanceLimitException("Cannot create more than " + MAXIMUM_INSTANCES + " instances of " + className);
        }
    }
}