package com.example.junithelloworld.geometry;

public class Rectangle {
    private Point origin;
    private Point opposite;

    public Rectangle(Point origin, Point opposite) {
        this.origin = origin;
        this.opposite = opposite;
    }

    public Rectangle(Point origin) {
        this.origin = this.opposite = origin;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getOpposite() {
        return opposite;
    }

    public void setOppositePoint(Point opposite) {
        this.opposite = opposite;
    }

    public int findArea() {
        return (int) (Math.abs(origin.x - opposite.x) *
            Math.abs(origin.y - opposite.y));
    }

    @Override
    public String toString() {
        return "Rectangle [origin " + origin + 
            ", opposite " + opposite + "]";
    }
}