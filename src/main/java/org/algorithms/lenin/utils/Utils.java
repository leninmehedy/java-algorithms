package org.algorithms.lenin.utils;

public class Utils {
    /**
     * Check for array out of bound index
     * Returns true if it is out of bound
     */
    public static <T> boolean oob(T[] items, int i) {
        if (i < 0 || i >= items.length) {
            return true;
        }

        return false;
    }

}
