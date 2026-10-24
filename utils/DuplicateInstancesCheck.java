package utils;
import java.util.*;

import classes.Cook;
import exceptions.DuplicateObjectException;

public class DuplicateInstancesCheck {
    public static void checkForDuplicate(HashSet<Object> instances, Object toCheck, String className) throws DuplicateObjectException{
        for (Object oneObject : instances) {
            if (oneObject.equals(toCheck)) {
                throw new DuplicateObjectException("Duplicate detected: " + className);
            }
        }
    }
}
