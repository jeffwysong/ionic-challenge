package com.wysong.ionicchallenge.rest;

import com.wysong.ionicchallenge.Point;

import java.util.Objects;
import java.util.Set;

public class CombinedReturnBody {
    private Set<Point> adjacents;
    private Point centerOfMass;

    public CombinedReturnBody() {
    }

    public CombinedReturnBody(Set<Point> adjacents, Point centerOfMass) {
        this.adjacents = adjacents;
        this.centerOfMass = centerOfMass;
    }

    public Set<Point> getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(Set<Point> adjacents) {
        this.adjacents = adjacents;
    }

    public Point getCenterOfMass() {
        return centerOfMass;
    }

    public void setCenterOfMass(Point centerOfMass) {
        this.centerOfMass = centerOfMass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombinedReturnBody that = (CombinedReturnBody) o;
        return Objects.equals(getAdjacents(), that.getAdjacents()) &&
                Objects.equals(getCenterOfMass(), that.getCenterOfMass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdjacents(), getCenterOfMass());
    }
}
