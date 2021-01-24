package org.algorithms.lenin.list;

public class ListNode {
    ListNode next;
    ListNode prev;

    int val;

    public ListNode(int v) {
        this.val = v;
    }

    public void setVal(int v) {
        this.val = v;
    }

    public int getVal() {
        return this.val;
    }

    public ListNode setNext(ListNode n) {
        this.next = n;
        return this.next;
    }

    public ListNode getNext() {
        return this.next;
    }

    public ListNode setPrev(ListNode n) {
        this.prev = n;
        return this.prev;
    }

    public ListNode getPrev() {
        return this.prev;
    }

    @Override
    public String toString() {
        return String.format("%d", this.getVal());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        ListNode n = (ListNode) o;

        String first = String.format("%s->%s->%s", this.getPrev(), this, this.getNext());
        String second = String.format("%s->%s->%s", n.getPrev(), n, n.getNext());

        return first.equals(second);
    }
}
