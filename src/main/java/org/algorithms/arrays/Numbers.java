package org.algorithms.arrays;

public class Numbers {
    /**
     * Clone even number twice
     *
     * Example: [2, 4, 2, 3, 6] ==> [2, 2, 4, 4, 2, 2, 3, 6, 6]
     *
     * Constraints:
     *  - Only integer?
     *  - Can we allocate new array?
     *      - No, the input will have extra spaces
     *  - Value of extra spaces
     *      - -1
     *  - Would input contain any other negatives?
     *
     *  Solution:
     *      - Two indexes
     *      - Traverse from the reverse and find the first non-negative
     *      - Copy even number twice or copy once
     *
     *  Complexity:
     *      - Computation: O(n)
     *      - Storage: O(1)
     *
     *  Test cases:
     *      Corner:
     *          - empty
     *          - null
     *          - single element
     *          - all empty (-1)
     *      Base:
     *          - one even
     *          - one odd
     *          - one even and one odd
     *      Regular:
     *          - Multiple numbers with mixed
     */
    public int[] cloneEven(int[] s) {
        if (s == null || s.length <= 1) return s;
        int end = s.length - 1;
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] == -1) continue;
            if (s[i] % 2 == 0) {
                s[end--] = s[i];
            }
            s[end--] = s[i];
        }

        return s;
    }

    /**
     * Find a pair of numbers in an array that equals to a given sum
     *
     * Example: [1, 2, 3, 4, 5] and 9 ==> [4, 5]
     *
     * Constraints:
     *  - Can there be negatives?
     *  - Can result be returned as an array?
     *  - Can there be multiple pairs? Which one to return
     *  - All integer?
     *  - Sorted or unsorted?
     *
     *  Solution:
     *      - Two index (start and end) at two ends of the array
     *      - At each iteration, use the logic below
     *          if sum of two numbers at the two indices is larger than sum, decrease the end index
     *          else increase the sum,
     *          until the pair is found or indices meets each other
     *
     * Complexity:
     *  - Computation: O(n)
     *  - Storage: O(1)
     *
     * Test cases:
     *  - Corner:
     *      - Null or empty
     *      - single element
     *      - no pair found
     *  - Base:
     *      - two numbers
     *      - three numbers
     *  - Regular:
     *      - more than three numbers with a single pair
     *      - more than three numbers with a multiple pair
     *
     */
    public int[] twoSum(int[] s, int sum) {
        if (s == null || s.length == 1) return null;

        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            int twoSum = s[start] + s[end];

            if (twoSum > sum ) end--;
            else if (twoSum < sum) start++;
            else {
                return new int[]{s[start], s[end]};
            }
        }

        return null;
    }

    /**
     * Rearrange the array so that all zeroes are at the beginning of the array.
     *
     * Example: [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]
     *
     * Constraints:
     *  - All integers?
     *  - Any particular order for the non-zero numbers?
     *  - Can we allocate new array? - Assume No
     *
     * Solution-1:
     *  - Two pointers one is the boundary, the other will be the scanner
     *  - Traverse from the beginning. Swap if there is a zero until scanner reaches the end.
     *
     * Complexity:
     *      - Computation: O(n)
     *      - Storage: O(1)
     *
     * TestCases
     *  - Corner:
     *      - Null or empty
     *      - one number
     *      - One zero
     *      - No zero
     *      - all zero
     *  - Regular:
     *      - Multiple zero
     */
    public int[] rearrangeZerosStart(int[] s) {
        if (s == null || s.length <= 1) return s;

        int i = 0;
        int j = 0;

        while (j < s.length) {
            if (s[j] == 0) {
                if (i != j) {
                    s[i] = s[i] ^ s[j];
                    s[j] = s[i] ^ s[j];
                    s[i] = s[i] ^ s[j];
                }
                i++;
            }
            j++;
        }
        return s;
    }

    public int[] rearrangeZerosEnd(int[] s) {
        if (s == null || s.length <= 1) return s;

        int i = s.length - 1;
        int j = s.length - 1;

        while (j >= 0) {
            if (s[j] == 0) {
                if (i != j) {
                    s[i] = s[i] ^ s[j];
                    s[j] = s[i] ^ s[j];
                    s[i] = s[i] ^ s[j];
                }
                i--;
            }
            j--;
        }
        return s;
    }


    /**
     * Arrange numbers in three groups based on pivot
     *
     * Example:  if A = [5,2,4,4,6,4,4,3] and pivot = 4 -> result = [3,2,4,4,4,4,6,5]
     *
     * Constraints:
     *   - Need storage O(1)
     *   - All integers?
     *
     * Solution:
     *   - Use three array partition technique
     *
     * Complexity:
     *   - Computation: O(n)
     *   - Storage: O(1)
     *
     * Test cases:
     *  - Empty or null
     *  - One element
     *  - two element
     *  - Tree elements
     *  - More than three with one pivot
     *  - More than three with multiple pivot
     */
    public int[] dutchFlag(int[] s, int pivot) {
        if (s == null || s.length <= 1) return s;

        int low = 0;
        int high = s.length - 1;
        int i = 0;

        while (i <= high) {
            if (s[i] < pivot) {
                swap(s, i, low);
                low++;
                i++;
            } else if (s[i] > pivot) {
                swap(s, i, high);
                high--;
            } else {
                i++;
            }
        }

        return s;
    }

    private void swap(int[] s, int i, int j) {
        if (i != j) {
            s[i] = s[i] ^ s[j];
            s[j] = s[i] ^ s[j];
            s[i] = s[i] ^ s[j];
        }
    }

    /**
     * Find the subarray with maximum sum
     *
     * Example: [-2, -3, 4, -1, -2, 1, 5, -1] => (2, 6)
     *
     * Constraints:
     *  - Both -ve and +ve integers
     *
     * Solution:
     *  - Kadane algorithm - O(n),
     *  - Brute force - O(n^2)
     *
     * Test case:
     *  - empty or null
     *  - one element
     *  - all positive
     *  - mixed of positive and negative
     */
    public int[] maxSumSubArray(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }

        int end = 0;
        int max_sum = a[0];
        int global_max = max_sum;

        for (int i = 1; i < a.length; i++) {
            max_sum = Math.max(a[i], a[i] + max_sum);
            if (max_sum > global_max) {
                end = i;
                global_max = max_sum;
            }
        }

        int sum = 0;
        for(int i = end; i >= 0; i--) {
            sum += a[i];
            if (sum == global_max) {
                return new int[]{i, end};
            }
        }

        // this should never happen
        throw new IllegalStateException("Could not find the maximum sum sub-array");
    }

    /**
     * Given an array, find a subarray that sums to a given target
     *
     * Example: [1, 2, 3, 5, 2) and target = 8, Result: [2, 3]
     *
     * Constraints:
     *  - Positive or Negative?
     *  - If not found what to return?
     *  - Null or empty would throw exception?
     *  - Contiguous subarray?
     *  - Empty subarray if target is zero?
     *
     *  Solution:
     *      - Sliding window technique
     *
     *  Test cases:
     *      - Null, empty,
     *      - single element with sum
     *      - Subarray exists
     *      - subarray doesn't exist
     */
    public int[] subArraySum(int[] a, int x) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }

        int start = 0, end = 0, sum = a[0];

        while(start < a.length) {
            if (start > end) {
                end = start;
                sum = a[start];
            }

            if (sum < x) {
                end++;
                if (end >= a.length) break;
                sum += a[end];
            } else if (sum > x) {
                sum -= a[start];
                start++;
            } else {
                return new int[]{start, end};
            }
        }

        throw new IllegalArgumentException("Sub-array does not exist");
    }

    public <A extends Comparable<A>> int binarySearch(A[] a, A target) {
        if (a == null || target == null) {
            return -1;
        }

        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = low + (high - low)/2;

            int comparedVal = a[mid].compareTo(target);
            if (comparedVal > 0) {
                high = mid - 1;
            } else if (comparedVal < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     *
     * Examples:
     *
     * Constraints:
     *
     * Solution - 1:
     * Complexity:
     *   - Computation:
     *   - Storage:
     *
     * Solution - 2:
     * Complexity
     *
     * Test cases:
     */
}
