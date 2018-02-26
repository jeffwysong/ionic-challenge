package com.wysong.ionicchallange;

public class Point extends java.awt.Point {

    public Point(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(x).append(",").append(y).append(")");
        return sb.toString();
    }
}
