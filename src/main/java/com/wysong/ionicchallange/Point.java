package com.wysong.ionicchallange;

//TODO: Refactor solution to add weight for convenience
public class Point extends java.awt.Point {

    public Point() {
        super();
    }

    public Point(int x, int y) {
        super(x, y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(x).append(",").append(y).append(")");
        return sb.toString();
    }
}
