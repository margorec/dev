package com.mycompany.quickCalculations.model;

import java.util.Comparator;

public class PointWithDistanceComparator implements Comparator<PointWithDistance> {

    @Override
    public int compare(PointWithDistance o1, PointWithDistance o2) {
        if (o1.getDistance() > o2.getDistance()) {
            return -1;
        }
        if (o1.getDistance() < o2.getDistance()) {
            return 1;
        } 
        return 0;
    }

}
