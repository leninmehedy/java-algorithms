package org.algorithms.lenin.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class NumbersTest {

    @Test
    public void testCloneEven() {
        Numbers nu = new Numbers();
        assertEquals(null, nu.cloneEven(null));
        assertArrayEquals(new int[]{0, 0}, nu.cloneEven(new int[]{0, -1}));
        assertArrayEquals(new int[]{-1, -1}, nu.cloneEven(new int[]{-1, -1}));
        assertArrayEquals(new int[]{1}, nu.cloneEven(new int[]{1}));
        assertArrayEquals(new int[]{2, 2, 4, 4, 1, 3, 6, 6}, nu.cloneEven(new int[]{2, 4, 1, 3, 6, -1, -1, -1}));
    }

    @Test
    public void testTwoSum() {
        Numbers nu = new Numbers();
        assertArrayEquals(null, nu.twoSum(null, 9));
        assertArrayEquals(null, nu.twoSum(new int[]{}, 9));
        assertArrayEquals(null, nu.twoSum(new int[]{9}, 9));
        assertArrayEquals(new int[]{1, 2}, nu.twoSum(new int[]{1, 2}, 3));
        assertArrayEquals(new int[]{2, 3}, nu.twoSum(new int[]{1, 2, 3}, 5));
        assertArrayEquals(new int[]{1, 6}, nu.twoSum(new int[]{1, 2, 3, 4, 5, 6}, 7));
    }

    @Test
    public void testTwoSumUnsorted() {
        Numbers nu = new Numbers();
        assertArrayEquals(null, nu.twoSumUnsorted(null, 9));
        assertArrayEquals(null, nu.twoSumUnsorted(new int[]{}, 9));
        assertArrayEquals(null, nu.twoSumUnsorted(new int[]{9}, 9));
        assertArrayEquals(new int[]{0, 1}, nu.twoSumUnsorted(new int[]{1, 2}, 3));
        assertArrayEquals(new int[]{0, 2}, nu.twoSumUnsorted(new int[]{1, 2, -3}, -2));
        assertArrayEquals(new int[]{2, 3}, nu.twoSumUnsorted(new int[]{1, 5, 3, 4, 2, 6}, 7));
    }


    @Test
    public void testRearrangeZerosStart() {
        Numbers nu = new Numbers();
        assertArrayEquals(null, nu.rearrangeZerosStart(null));
        assertArrayEquals(new int[]{}, nu.rearrangeZerosStart(new int[]{}));
        assertArrayEquals(new int[]{9}, nu.rearrangeZerosStart(new int[]{9}));
        assertArrayEquals(new int[]{0}, nu.rearrangeZerosStart(new int[]{0}));
        assertArrayEquals(new int[]{0, 0, 1}, nu.rearrangeZerosStart(new int[]{0, 1, 0}));
        assertArrayEquals(new int[]{0, 2, 1}, nu.rearrangeZerosStart(new int[]{1, 2, 0}));
    }


    @Test
    public void testRearrangeZerosEnd() {
        Numbers nu = new Numbers();
        assertArrayEquals(null, nu.rearrangeZerosEnd(null));
        assertArrayEquals(new int[]{}, nu.rearrangeZerosEnd(new int[]{}));
        assertArrayEquals(new int[]{9}, nu.rearrangeZerosEnd(new int[]{9}));
        assertArrayEquals(new int[]{0}, nu.rearrangeZerosEnd(new int[]{0}));
        assertArrayEquals(new int[]{1, 0, 0}, nu.rearrangeZerosEnd(new int[]{0, 1, 0}));
        assertArrayEquals(new int[]{2, 1, 0}, nu.rearrangeZerosEnd(new int[]{0, 1, 2}));
    }


    @Test
    public void testDutchFlag() {
        Numbers nu = new Numbers();
        assertArrayEquals(null, nu.dutchFlag(null, 4));
        assertArrayEquals(new int[]{}, nu.dutchFlag(new int[]{}, 4));
        assertArrayEquals(new int[]{9}, nu.dutchFlag(new int[]{9}, 4));
        assertArrayEquals(new int[]{1, 2}, nu.dutchFlag(new int[]{1, 2}, 4));
        assertArrayEquals(new int[]{1, 4, 5}, nu.dutchFlag(new int[]{5, 4, 1}, 4));
        assertArrayEquals(new int[]{3,2,4,4,4,4,6,5}, nu.dutchFlag(new int[]{5,2,4,4,6,4,4,3}, 4));
        assertArrayEquals(new int[]{0,0,1,1,1,1,2,2}, nu.dutchFlag(new int[]{1,0,1,2,1,0,1,2}, 1));
    }

    @Test
    public void testMaxSumSubArray() {
        Numbers nu = new Numbers();
        try {
            nu.maxSumSubArray(null);
            nu.maxSumSubArray(new int[]{});
        } catch (IllegalArgumentException e) {}

        assertArrayEquals(new int[]{0, 0}, nu.maxSumSubArray(new int[]{0}));
        assertArrayEquals(new int[]{1, 2}, nu.maxSumSubArray(new int[]{0, 1, 2}));
        assertArrayEquals(new int[]{0, 0}, nu.maxSumSubArray(new int[]{0, -1, -2}));
        assertArrayEquals(new int[]{2, 6}, nu.maxSumSubArray(new int[]{-2, -3, 4, -1, -2, 1, 5, -1}));
    }

    @Test
    public void testSquareArry() {
        Numbers nu = new Numbers();
        assertEquals(null, nu.squareArray(null));
        assertArrayEquals(new int[]{}, nu.squareArray(new int[]{}));
        assertArrayEquals(new int[]{1}, nu.squareArray(new int[]{-1}));
        assertArrayEquals(new int[]{1, 4}, nu.squareArray(new int[]{1, 2}));
        assertArrayEquals(new int[]{4, 4}, nu.squareArray(new int[]{-2, -2}));
        assertArrayEquals(new int[]{4, 4, 9}, nu.squareArray(new int[]{-3, -2, 2}));
        assertArrayEquals(new int[]{0, 1, 4, 9, 16, 25}, nu.squareArray(new int[]{-4, -2, -1, 0, 3, 5}));
    }

    public void testSubArraySum() {
        Numbers nu = new Numbers();
        try {
            nu.subArraySum(null, 8);
            nu.subArraySum(new int[]{}, 8);
            nu.subArraySum(new int[]{1,2}, 8);
        } catch (IllegalArgumentException e) {}

        assertArrayEquals(new int[]{0, 0}, nu.subArraySum(new int[]{8}, 8));
        assertArrayEquals(new int[]{2, 3}, nu.subArraySum(new int[]{1, 2, 3, 5, 2}, 8));
    }

    @Test
    public void testPrefixSum() {
        Numbers nu = new Numbers();
        assertEquals(null, nu.prefixSum(null));
        assertEquals(null, nu.prefixSum(new int[]{}));
        assertEquals(null, nu.prefixSum(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{1, 4}, nu.prefixSum(new int[]{2,4,-2,1,-3,5,-3}));
    }
}