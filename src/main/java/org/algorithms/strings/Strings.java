package org.algorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * SentenceUtil exposes various utility functions to process a sentence
 *
 */
public class Strings {
    /**
     * ReverseWords reverse the words in a sentence
     *
     * Example: "i live in a house" ==> "house a in live i"
     *
     * Constraints:
     *  - Capital cases? or case sensitive
     *  - punctuation?
     *  - single space as word separator?
     *  - can we allocate new array?
     *
     * Solution-1:
     *  - Two indices for source forward
     *  - One index to track dst from reverse

     *
     * Complexity:
     *  - Computation: O(n^2)
     *  - Storage: O(n)
     *
     * Test cases:
     *  - Corner:
     *      - null
     *      - empty
     *      - single character
     *  - Base:
     *      - Single word
     *  - Regular:
     *      - Multiple words
     *  - Exceptional:
     *      - single space
     *      - all spaces
     *      - Multiple spaces between words
     *      - Sentence ending with space(s)
     */
    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] src = s.toCharArray();
        char[] dst = new char[src.length];
        int i, j, k;
        j = 0;
        k = dst.length - 1;

        for (i = 0; i < src.length; i++) {
            if (src[i] != ' ') continue;
            k = copyWord(src, i, j, dst, k);
            j = i + 1;
        }

        if (i > j) {
            copyWord(src, i, j, dst, k);
        }

        return String.valueOf(dst);
    }

    private int copyWord(char[] src, int i, int j, char[] dst, int k) {
        for (int n = i - 1; n >= j; n--) {
            dst[k--] = src[n];
        }

        if (i < src.length) {
            dst[k--] = src[i]; // copy the word separator
        }

        return k;
    }

    /**
     * ReverseWords reverse the words in a sentence
     *
     * Example: "i live in a house" ==> "house a in live i"
     *
     * Constraints:
     *  - Capital cases? or case sensitive
     *  - punctuation?
     *  - single space as word separator?
     *  - can we allocate new array?
     *
     * Solution-2:
     *  - Traverse the src from reverse until word separator is found
     *  - Use StringBuilder to append word
     *
     * Complexity:
     *  - Computation: O(n^2)
     *  - Storage: O(n)
     *
     * Test cases:
     *  - Corner:
     *      - null
     *      - empty
     *      - single character
     *  - Base:
     *      - Single word
     *  - Regular:
     *      - Multiple words
     *  - Exceptional:
     *      - single space
     *      - all spaces
     *      - Multiple spaces between words
     *      - Sentence ending with space(s)
     */
    public String reverseWords2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        int end = s.length();

        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == ' ') {
                copyWord2(builder, s, i+1, end);
                end = i;
            }
        }

        copyWord2(builder, s, 0, end);

        return builder.toString();
    }

    private void copyWord2(StringBuilder builder, String src, int start, int end) {
        if (builder.length() > 0) builder.append(' ');
        builder.append(src.substring(start, end));
    }

    /**
     * Find the first longest substring with unique characters
     *
     * Example: "whatwhywhere" --> "atwhy"
     *
     * Constraints:
     *  - What to return
     *  - What about null or empty string - return null
     *  - All alphabets? - No any character
     *
     * Solution:
     *  - Sliding window with a hashmap to keep track of found characters
     *
     * TestCases:
     *  - Null and empty
     *  - One character string
     *  - substring at the beginning
     *  - substring in the middle
     *  - substring at the end
    **/
    public int[] longestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        Map<Character, Integer> tracker = new HashMap();
        int start = 0, end = 0, maxLength = -1, len = -1;
        int rx = 0, ry = 0;

        while(end < s.length()) {
            Character ch = s.charAt(end);
            if (tracker.containsKey(ch)) {
                start = tracker.get(ch)+1;
            }

            len = end - start + 1;
            if (len > maxLength) {
                maxLength = len;
                rx = start;
                ry = end;
            }

            tracker.put(ch, end);
            end++;
        }

        return new int[]{rx, ry};
    }
}
