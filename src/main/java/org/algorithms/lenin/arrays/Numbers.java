package org.algorithms.lenin.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Numbers {
    /**
     * Clone even number twice
     *
     * Example: [2, 4, 2, 3, 6] ==> [2, 2, 4, 4, 2, 2, 3, 6, 6]
     *
     * Constraints:
     *  - Only integer?
     *      - Yes
     *  - Can we allocate new array?
     *      - No, the input will have extra spaces
     *  - Value of extra spaces
     *      - -1
     *  - Would input contain any other negatives?
     *      - No
     *
     *  Solution:
     *      - Two indexes
     *      - Traverse from the reverse and find the first non-negative
     *      - Copy twice if it is an even number twice otherwise copy once
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
     *          - array with only one even number
     *          - array with only one odd number
     *          - array with one even and one odd number
     *      Regular:
     *          - Multiple numbers with mixed types (i.e. even or odd)
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
     * Square the number in a sorted array and return in sorted format
     *
     * Examples: [-4, -2, -1, 0, 3, 5] -> [0, 1, 4, 9, 16, 25]
     *
     * Constraints:
     *   1. Signed integer
     *   2. For null or empty array, return the array itself
     *   3. You may or may not allocate new array
     *   4. Duplicates may exists
     *
     * Solution - 1:
     *   - Track the smallest at 0
     *   - Search from the last and swap with the smallest at 0
     *   - If the new position is larger than the last, swap
     * Complexity:
     *   - Computation: O(N)
     *   - Storage: O(1)
     *
     * Test cases:
     *   - Edge cases: null, empty array
     *   - Base case: one positive, one negative, two positive, two negative, two duplicates, two with one positive and one negative
     *   - Regular cases: 
     *       three with mixed
     *       three with duplicates
     *       5 with mixed (given example)
     */
    public int[] squareArray(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }

        a[0] = a[0] * a[0];
        int j = a.length - 1;
        while(j > 0) {
            a[j] = a[j] * a[j];
            if (a[0] > a[j]) {
                swap(a, 0, j);
            } else if (j <= a.length - 2 && a[j] > a[j+1]) {
                swap(a, j, j+1);
            }
            j--;
        }

        return a;
    }
    /**
     * Given an array of integers, both -ve and +ve, find a contiguous subarray that sums to 0.
     * Examples: [2,4,-2,1,-3,5,-3] --> [4,-2,1,-3]
     *
     * Questions:
     *  - What to return? 
     *      - Return start and end indices
     *      - Return null if there is no subarray
     *      - Return null if the input is null or empty
     *
     * Solution:
     *   - Compute sum for each index
     *   - If the sum is zero then return start and current pos
     *   - If the sum has been seen before, return the (last index + 1, current index)
     *   - Otherwise put the sum and index in the hasmap and go to the next index
     * Complexity:
     *   - Computation: O(N)
     *   - Storage: O(N)
     *
     * Test cases:
     */
    public int[] prefixSum(int[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        int sum = a[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, 0);

        for (int i = 1; i < a.length; i++) {
            sum += a[i];

            if (sum == 0) {
                return new int[]{0, i};
            }

            if (map.containsKey(sum)) {
                return new int[]{map.get(sum) + 1, i};
            }

            map.put(sum, i);
        }

        return null;
    }

    /**
     * Find a pair of numbers in an unsorted array that equals to a given sum
     *
     * Example: [1, 5, 2, 4, 3] and 9 ==> indices [1, 3]
     *
     * Constraints:
     *  - Can there be negatives? - Yes
     *  - Can result be returned as an array? - Yes, the indices
     *  - Can there be multiple pairs? Which one to return - Return the first one
     *  - All integer? - Yes
     *  - Sorted or unsorted? - Unsorted
     *
     *  Solution:
     *      - Hashmap to store the difference and index
     *      - Scan the array and see if the difference is in the hasmap
     *      - If the difference is found, return the two indices
     *
     * Complexity:
     *  - Computation: O(n)
     *  - Storage: O(n)
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
    public int[] twoSumUnsorted(int[] s, int sum) {
        if (s == null || s.length == 0) return null;
        Map<Integer, Integer> candidates = new HashMap<>();
        candidates.put(s[0], 0);
        for (int i = 1; i < s.length; i++) {
            int diff = sum - s[i];
            if (candidates.containsKey(diff)) {
                return new int[]{candidates.get(diff), i};
            }
            
            candidates.put(s[i], i);
        }

        return null;
    }

    /**
     * Find the first occurance of a number in a sorted array
     * Examples: [1, 2, 3, 3, 4, 5] and target=3, ans: 2
     *
     * Constraints:
     *   - If number is not found, return -1
     *   - if null or empty array, return -1
     *   - integers only
     *
     * Solution: binary search with shifting left
     * Complexity:
     *   - Computation: O(log(n))
     *   - Storage: O(1)
     *
     * Test cases:
     */
    public int firstOccurence(int[] a, int t) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if (a[mid] > t || a[mid] == t && mid > 0 && a[mid - 1] == t) {
                high = mid - 1;
            } else if(a[mid] < t) {
                low = mid + 1;
            } else {
                return mid;
            }
        }


        return -1;
    }

    /**
     * Find the last occurance of a number in a sorted array
     * Examples: [1, 2, 3, 3, 4, 5] and target=3, ans: 3
     *
     * Constraints:
     *   - If number is not found, return -1
     *   - if null or empty array, return -1
     *   - integers only
     *
     * Solution: binary search with shifting right 
     * Complexity:
     *   - Computation: O(log(n))
     *   - Storage: O(1)
     *
     * Test cases:
     */
    public int lastOccurence(int[] a, int t) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if (a[mid] > t) {
                high = mid - 1;
            } else if(a[mid] < t || a[mid] == t && mid < a.length - 1 && a[mid + 1] == t) {
                low = mid + 1;
            } else {
                return mid;
            }
        }


        return -1;
    }

    /**
     * Find the first insertion index of a number in a sorted array
     * Examples: [1, 2, 4, 4, 4, 5] and target=3, ans: 2
     *
     * Constraints:
     *   - If number is not found, return -1
     *   - if null or empty array, return -1
     *   - integers only
     *
     * Solution: binary search with shifting right 
     * Complexity:
     *   - Computation: O(log(n))
     *   - Storage: O(1)
     *
     * Test cases:
     */
    public int firstInsertionIndex(int[] a, int t) {
        if (a == null) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;

        if (a.length == 0 || a[low] >= t) {
            return low;
        }

        if (a[high] < t) {
            return high;
        }

        while(low <= high) {
            int mid = low + (high - low)/2;
            if (a[mid] >= t) {
                high = mid - 1;
            } else if(a[mid] < t ) {
                if (mid + 1 < a.length && a[mid + 1] >= t)  {
                    return mid + 1; 
                }
                low = mid + 1;
            }
        }

        throw new IllegalStateException(String.format("Shouldn't be here: %s , t: %d", Arrays.toString(a), t));
    }

    /**
     * Find the last insertion index of a number in a sorted array
     * Examples: [1, 2, 4, 4, 4, 5] and target=4, ans: 5
     *
     * Constraints:
     *   - If number is not found, return -1
     *   - if null or empty array, return -1
     *   - integers only
     *
     * Solution: binary search with shifting right 
     * Complexity:
     *   - Computation: O(log(n))
     *   - Storage: O(1)
     *
     * Test cases:
     */
    public int lastInsertionIndex(int[] a, int t) {
        if (a == null) {
            return -1;
        }

        int low = 0;
        int high = a.length - 1;

        if (a.length == 0 || a[low] > t) {
            return low;
        }

        if (a[high] <= t) {
            return high + 1;
        }

        while(low <= high) {
            int mid = low + (high - low)/2;
            if (a[mid] > t) {
                high = mid - 1;
            } else if(a[mid] <= t ) {
                if (mid + 1 < a.length && a[mid + 1] > t)  {
                    return mid + 1; 
                }
                low = mid + 1;
            }
        }

        throw new IllegalStateException(String.format("Shouldn't be here: %s , t: %d", Arrays.toString(a), t));
    }

    /**
     * Given a sorted array of Integers, find the target. 
     * If the target is not found,return the smallest element that is closest to the target.
     * 
     * Examples: Given, A = [1,2,4,5,7,8,9], Target = 6 -> Output Index = 3 (since both 5 and 7 are equally close)
     *
     * Constraints:
     *   - What is the output: return index
     *   - What if there are multiple, which one to return: Return the first
     *   - If array is null or empty: Return -1
     *   - Will there be any negative? Yes.
     *
     * Solution: Binary search. Record the smallest found so far and move on. Return the closest found
     * Complexity:
     *   - Computation: O(log(N))
     *   - Storage: O(1)
     *
     * Test cases:
     *  - Empty or null array
     *  - Single element array with the target
     *  - Single element array with a number other than target
     *  - Three elements array with the target
     *  - Three elements array with multiple answers
     *  - Multiple elements array with single match
     *  - Multiple elements array with multiple answers 
     *  - Multiple elements array with negatives and multiple answers 
     */
    public int closestFirstMatch(int[] a, int t) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int closest = -1;
        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if (closest == -1 || (Math.abs(a[mid] - t) < Math.abs(a[closest] - t) && a[closest] > a[mid])) {
                closest = mid;
            }

            if (a[mid] > t) {
                high = mid - 1;
            } else if (a[mid] < t) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return closest;
    }

    /**
     * Given a sorted array of Integers, find the target. 
     * If the target is not found,return the largest element that is closest to the target.
     * 
     * Examples: Given, A = [1,2,4,5,7,8,9], Target = 6 -> Output Index = 4 (since both 5 and 7 are equally close)
     *
     * Constraints:
     *   - What is the output: return index
     *   - What if there are multiple, which one to return: Return the first
     *   - If array is null or empty: Return -1
     *   - Will there be any negative? Yes.
     *
     * Solution: Binary search. Record the smallest found so far and move on. Return the closest found
     * Complexity:
     *   - Computation: O(log(N))
     *   - Storage: O(1)
     *
     * Test cases:
     *  - Empty or null array
     *  - Single element array with the target
     *  - Single element array with a number other than target
     *  - Three elements array with the target
     *  - Three elements array with multiple answers
     *  - Multiple elements array with single match
     *  - Multiple elements array with multiple answers 
     *  - Multiple elements array with negatives and multiple answers 
     */
    public int closestLastMatch(int[] a, int t) {
        if (a == null || a.length == 0) {
            return -1;
        }

        int closest = -1;
        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if (closest == -1 || (Math.abs(a[mid] - t) < Math.abs(a[closest] - t) && a[closest] < a[mid])) {
                closest = mid;
            }

            if (a[mid] > t) {
                high = mid - 1;
            } else if (a[mid] < t) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return closest;
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
