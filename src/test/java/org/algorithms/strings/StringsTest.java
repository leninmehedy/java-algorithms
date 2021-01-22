package org.algorithms.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {

    @Test
    public void testReverseWords() {
        Strings su = new Strings();

        assertEquals(null, su.reverseWords(null));
        assertEquals("", su.reverseWords(""));
        assertEquals("i", su.reverseWords("i"));
        assertEquals("live", su.reverseWords("live"));
        assertEquals("live i", su.reverseWords("i live"));

        // Here are some cases which are however invalid sentences since there are multiple spaces
        assertEquals(" live i", su.reverseWords("i live "));
        assertEquals("  live i", su.reverseWords("i live  "));
        assertEquals(" live  i", su.reverseWords("i  live "));
    }

    @Test
    public void testReverseWords2() {
        Strings su = new Strings();

        assertEquals(null, su.reverseWords2(null));
        assertEquals("", su.reverseWords2(""));
        assertEquals("i", su.reverseWords2("i"));
        assertEquals("live", su.reverseWords2("live"));
        assertEquals("house a in live i", su.reverseWords2("i live in a house"));

        // Here are some cases which are however invalid sentences since there are multiple spaces
//        assertEquals(" live i", su.reverseWords2("i live "));
//        assertEquals("  live i", su.reverseWords2("i live  "));
//        assertEquals(" live  i", su.reverseWords2("i  live "));
    }

    @Test
    public void testLongestUniqueSubstring() {
        Strings su = new Strings();
        assertArrayEquals(null, su.longestUniqueSubstring(null));
        assertArrayEquals(null, su.longestUniqueSubstring(""));
        assertArrayEquals(new int[]{0, 0}, su.longestUniqueSubstring("w"));
        assertArrayEquals(new int[]{0, 4}, su.longestUniqueSubstring("wherxwhere")); // at the beginning
        assertArrayEquals(new int[]{2, 6}, su.longestUniqueSubstring("whatwhywhere")); // in the middle
        assertArrayEquals(new int[]{2, 6}, su.longestUniqueSubstring("whatwhy")); // at the end
    }
}