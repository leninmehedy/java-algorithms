package org.algorithms.lenin.list;

public class MedianPair {
    ListNode median;
    ListNode prev;

    public MedianPair(ListNode median, ListNode prev) {
        this.median = median;
        this.prev = prev;
    }

    public ListNode getMedian() {
        return this.median;
    }

    public ListNode getMedianPrev() {
        return this.prev;
    }

    @Override
    public String toString() {
        return String.format("%s->%s", this.getMedianPrev(), this.getMedian());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        MedianPair m = (MedianPair) o;
        String first = String.format("%s->%s", this.getMedianPrev(), this.getMedian());
        String second = String.format("%s->%s", m.getMedianPrev(), m.getMedian());

        return first.equals(second);
    }
}
