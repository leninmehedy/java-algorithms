package org.algorithms.lenin.list;

public class LinkedList {

    ListNode head;
    ListNode tail;

    /**
     * Append a node to the linked list
     *
     * Complexity:
     *  - Space: O(1)
     *  - Time: O(n)
     *
     *  Test cases:
     *   - Edge case: null
     *   - Base case: one node
     *   - Regular case: new node to an existing list
     */
    public void add(int val) {
        if (head == null) {
            head = new ListNode(val);
            tail = head;
            return;
        }

        ListNode n = new ListNode(val);
        tail.setNext(n);
        tail = n;
    }

    public ListNode getHead() {
        return this.head;
    }

    public ListNode getTail() {
        return this.tail;
    }


    /**
     * Find the median of a linked list
     *
     * Solution: use fast and slow pointer method
     *
     * Complexity:
     *  Space: O(1)
     *  Time: O(n)
     *
     *  TestCases:
     *   - Edge cases: null values
     *   - Base cases: one node, two nodes, three nodes
     *   - Regular cases: even nodes, odd nodes
     */
    public static MedianPair medianPair(ListNode head, ListNode tail) {
        if (head == null || tail == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPrev = null;

        while(fast != tail) {
            fast = fast.getNext();
            if (fast != tail) {
                fast = fast.getNext();
                slowPrev = slow;
                slow = slow.getNext();
            }
        }

        return new MedianPair(slow, slowPrev);
    }
}
