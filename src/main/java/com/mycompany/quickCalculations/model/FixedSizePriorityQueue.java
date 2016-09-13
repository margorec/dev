package com.mycompany.quickCalculations.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FixedSizePriorityQueue extends PriorityQueue<PointWithDistance> {
    private final short limit;
    private static final PointWithDistanceComparator COMPARATOR = new PointWithDistanceComparator();

    private FixedSizePriorityQueue(short limit, Comparator c) {
        super (c);
        this.limit = limit;
    }
    
    public static FixedSizePriorityQueue getNormalQueue(short limit) {
        return new FixedSizePriorityQueue(limit, COMPARATOR);
    }
    
    public static FixedSizePriorityQueue getReversedQueue(short limit) {
        return new FixedSizePriorityQueue(limit, COMPARATOR.reversed());
    }
    
    @Override
    public boolean add(PointWithDistance e) {   
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
