package org.algorithms.lenin.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void medianPair() {
        LinkedList list = new LinkedList();
        list.add(0);

        // Edge cases
        assertNull(LinkedList.medianPair(null, null));
        assertNull(LinkedList.medianPair(null, list.getTail()));
        assertNull(LinkedList.medianPair(list.getHead(), null));

        // Base cases: single node
        assertEquals(new MedianPair(new ListNode(0), null), LinkedList.medianPair(list.getHead(), list.getTail()));

        // Base cases: two nodes
        list.add(1);
        assertEquals(new MedianPair(new ListNode(0), null), LinkedList.medianPair(list.getHead(), list.getTail()));

        // Base cases: three nodes
        list.add(2);
        assertEquals(new MedianPair(new ListNode(1), new ListNode(0)), LinkedList.medianPair(list.getHead(), list.getTail()));

        // Regular cases: even nodes (6 nodes)
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(new MedianPair(new ListNode(2), new ListNode(1)), LinkedList.medianPair(list.getHead(), list.getTail()));

        // Regular cases: odd nodes (5 nodes)
        list.add(6);
        assertEquals(new MedianPair(new ListNode(3), new ListNode(2)), LinkedList.medianPair(list.getHead(), list.getTail()));
    }
}