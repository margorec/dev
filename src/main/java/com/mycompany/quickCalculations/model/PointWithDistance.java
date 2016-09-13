package com.mycompany.quickCalculations.model;

import com.google.common.base.MoreObjects;

public class PointWithDistance {

    private final short x;
    private final short y;
    private final double distance;

    public PointWithDistance(short x, short y, short startX, short startY) {
        this.x = x;
        this.y = y;
        this.distance = this.getDistance(startX, startY);
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).
                add("Distance", distance).
                add("x", x).
                add("y", y).
                toString();
    }

    private double getDistance(short startX, short startY) {
        return Math.sqrt(Math.pow((x - startX), 2) + Math.pow((y - startY), 2));
    }
}
