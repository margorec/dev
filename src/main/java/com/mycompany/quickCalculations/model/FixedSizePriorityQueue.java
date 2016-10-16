package com.mycompany.quickCalculations.model;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class FixedSizePriorityQueue extends PriorityBlockingQueue<PointWithDistance> {
    private final short limit;
    private static final PointWithDistanceComparator COMPARATOR = new PointWithDistanceComparator();

    private FixedSizePriorityQueue(final short limit, final Comparator c) {
        super (limit, c);
        this.limit = limit;
    }
    
    public static FixedSizePriorityQueue getNormalQueue(final short limit) {
        return new FixedSizePriorityQueue(limit, COMPARATOR);
    }
    
    public static FixedSizePriorityQueue getReversedQueue(final short limit) {
        return new FixedSizePriorityQueue(limit, COMPARATOR.reversed());
    }
    
    @Override
    public boolean add(final PointWithDistance e) {   
        if (this.size() == limit && this.comparator().compare(this.peek(), e) > 0) {
            return false;
        }
        boolean result = super.add(e);
        if (this.size() > limit) {
            this.poll();
        }
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        while (!this.isEmpty()) {
            sb.append(this.remove().toString()).append("\n");
        }
        return sb.toString();
    } 
}
