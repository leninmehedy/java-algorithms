package org.algorithms.search;

public class Search {
    public static <T extends Comparable<T>> int binary(T[] items, T target) {
        if (null == items) {
            throw new IllegalArgumentException("Items cannot be null");
        }

        if (null == target) {
            throw new IllegalArgumentException("Target search item cannot be null");
        }

        int low = 0;
        int high = items.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int diff = items[mid].compareTo(target);

            if (diff > 0) {
                high = mid - 1;
            } else if (diff < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
