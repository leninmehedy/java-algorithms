package org.algorithms.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {

    @Test
    public void binary() {
        Integer[] items = new Integer[]{2, 5, 7, 9, 11, 13, 24};
        assertEquals(3, Search.binary(items, 9));

        assertThrows(IllegalArgumentException.class, () -> {
            Search.binary(null, 9);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Search.binary(items, null);
        });
    }
}