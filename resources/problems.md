# Traversing Array in Reverse
1. Level: EasyGiven an array of numbers, replace each even number with two of the same number. e.g, [1,2,5,6,8, , , ,] -> [1,2,2,5,6,6,8,8]
Assume that the array has the exact amount of space to accommodate the result.
2. Given a sentence, reverse the words of the sentence. For example, "i live in a house" becomes "house a in live i".

# Traverse from Both Ends
3. (Level: Easy) Reverse the order of elements in an array. For example, A = [1,2,3,4,5,6], Output = [6,5,4,3,2,1]
4. (Level: Easy) Two Sum Problem - Find 2 numbers in a sorted array that sum to X. For example, if A = [1,2,3,4,5] and X = 9, the numbers are 4 and 5.
5. Given a sorted array in non-decreasing order, return an array of squares of each number, also in non-decreasing order. For example:
[-4,-2,-1,0,3,5] -> [0,1,4,9,16,25]
How can you do it in O(n) time?
6. Given an array of integers, find the continuous subarray, which when sorted, results in the entire array being sorted. For example: A = [0,2,3,1,8,6,9], result is the subarray [2,3,1,8,6]

# Partitioning Arrays
7. You are given an array of integers. Rearrange the array so that all zeroes are at the beginning of the array.
For example, [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]
8. Now, given an array, move all zeroes to the end of the array. For example, [4,2,0,1,0,3,0] -> [4,1,2,3,0,0,0]
9.  Dutch National Flag Problem: Given an array of integers A and a pivot, rearrange A in the following order:
[Elements less than pivot, elements equal to pivot, elements greater than pivot]
For example, if A = [5,2,4,4,6,4,4,3] and pivot = 4 -> result = [3,2,4,4,4,4,6,5]
10. Given an array with n marbles colored Red, White or Blue, sort them so that marbles of the same color are adjacent, with the colors in the order Red, White and Blue.
Assume the colors are given as numbers - 0 (Red), 1 (White) and 2 (Blue).
For example, if A = [1,0,1,2,1,0,1,2], Output = [0,0,1,1,1,1,2,2].

#  Kadane's Algorithm
11. Given an array of integers that can be both +ve and -ve, find the contiguous subarraywith the largest sum.For example:  [1,2,-1,2,-3,2,-5]  -> first 4 elements have the largest sum. Return (0,3)

# Sub-array
12. Given an array of positive integers, find a subarray that sums to a given number X.For e.g, input = [1,2,3,5,2] and X=8, Result = [3,5] (indexes 2,3)
13. Given a String, find the longest substring with unique characters.For example: "whatwhywhere" --> "atwhy"

# Prefix Sum
14. Given an array of integers, both -ve and +ve, find a contiguous subarray that sums to 0.For example: [2,4,-2,1,-3,5,-3] --> [4,-2,1,-3]
15. Given an array of positive and negative integers, find a subarray whose sum equals X.
For example: Input = [2,4,-2,1,-3,5,-3], X = 5 --> Result = [2,4,-2,1]

# Binary Search
16. Given a sorted array that can contain duplicates, find the first occurrence of the target element. For example:A = [1,3,4,6,6,6,7] and Target = 6, return index 3.
17. You are given a sorted array A and a target T. Return the index where it would be placed if inserted in order.For example,
A = [1,2,4,4,5,6,8] and T = 3, return index 2
A = [1,2,4,4,5,6,8] and T = 0, return index 0
A = [1,2,4,4,5,6,8] and T = 4, return index 4 (insert after other 4’s)
18. Given a sorted array A and a target T, find the target. If the target is not in the array, find the number closest to the target. For example, if A = [2,3,5,8,9,11] and T = 7, return 8.
19. Given a sorted array A that has been rotated in a cycle, find the smallest element of the array in O(log(n)) time. For example,
[1,2,4,5,7,8] rotated left by 3 gives us A = [5,7,8,1,2,4] and the smallest number is 1.
[1,2,4,5,7,8] rotated right by 1 gives us A = [8,1,2,4,5,7] and the smallest number is 1.
20. Search Array of Unknown lengthYou are given an array, but you don't know the length. Write a program to finda target element in the array

# Recursion
21. Fibonacci problem with memoization
22. Implement a function to calculate x^n. Both x and n can be positive/negative and overflow doesn't happen. Try doing it in O(log(n)) time.
22. Given an array of integers, print all combinations of size X.
23. Phone Number Mnemonics: Given an N digit phone number, print all the strings that can be made from that phone number. Since 1 and 0 don't correspond to any characters, ignorethem.For example:
213 => AD, AE, AF, BD, BE, BF, CE, CE, CF
456 => GJM, GJN, GJO, GKM, GKN, GKO,.. etc
24. Given an array of integers A, print all its subsets.For example:
Input:​ [1, 2, 3]
Output:
[]
[1]
[2]
[3]
[1, 2]
[1, 3]
[2, 3]
[1, 2, 3]
25. Given an array A, print all permutations of size X.
For example,
Input:A = [1,2,3] X = 2
Output:
[1, 2]
[1, 3]
[2, 1]
[2, 3]
[3, 1]
[3, 2]

# Backtracking
26. You are given a 2D array that represents a maze. It can have 2 values - 0 and 1. 1 represents a wall and 0 represents a path. The objective of the maze is to reach the bottom right corner, or A[A.length-1][A.length-1]. You start from A[0][0] and can only go in 4 directions - up, down, left or right. Find if a path exists.
27. Given a String S, which contains letters and no spaces, find if it can be broken it into valid words. Return one such combination of words. Assume you are provided a dictionary of English words.

For example:

S = "ilikemangotango"

Output: any one of the following:

"i like mango tango"

"i like man go tan go"

"i like mango tan go"

"i like man go tango"
28. Sudoku Solver: Given a 9x9 partially filled array, find a way to fill it such that each row, column and 3x3 sub-grid contain exactly one instance of digits 1-9.

# Linked List
29. Given a LinkedList L,separate it into 2 Linked Lists.One contains L's odd graphNodes and the ther contains L's even graphNodes
30. Given a Linked List, find if it has a cycle.
31. If you found a cycle in a linked list, find the length of the cycle.
32. Given a linked list, find its median graphNode. You may assume the list does not have a cycle.
33. Find the 3rd to last element in a given linked list. 
34. Given a Linked List with a cycle, find the graphNode where the cycle begins.

# Stack
35. Implement a Queue using 2 Stacks.
36. Use an array to implement 2 Stacks.
37. Implement a Stack with O(1) lookup of the maximum element in the stack.
38. Given an arithmetic expression with *,/,- & + operators and single digit numbers,evaluate it and return the result.For example,1 + 2 / 1 + 3 * 2 ==> 9
39. Same as 38, but when it is Postfix expression
38. Same as 38 but it can also contain parenthesis

# Queue
39. Implement a Queue using an array.
40. Given an array of integers A, find the sum of sliding windows of size N. For example: if A = [2,3,5,6,2,1]
Sliding Window Sums for 3:
[2,3,5,6,2,1] => 10
[2,3,5,6,2,1] => 14
[2,3,5,6,2,1] => 13
[2,3,5,6,2,1] => 9
41. Same as 40,  Instead of an array, what if you were presented with a stream of numbers. A new number can be added anytime. You want to find the sum of the last K elements.
42. You are given stock prices and the corresponding day of each stock price.For example:(32, 1), (45, 1), (37,2), (42,3), ..Here, 32 is the price and 1 is the day of the price.Say you are given these prices as an input stream. You should provide a function for the user to input a stock price and day. Your system should be able to tell the maximum stock price in the last 3 days
