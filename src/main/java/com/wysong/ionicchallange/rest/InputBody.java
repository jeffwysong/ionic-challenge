package com.wysong.ionicchallange.rest;

import java.util.Arrays;
import java.util.Objects;

public class InputBody {
    private Integer[][] inputMatrix;
    private int threshold;

    public InputBody() {
    }

    public InputBody(Integer[][] inputMatrix, int threshold) {
        this.inputMatrix = inputMatrix;
        this.threshold = threshold;
    }

    public Integer[][] getInputMatrix() {
        return inputMatrix;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setInputMatrix(Integer[][] inputMatrix) {
        this.inputMatrix = inputMatrix;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputBody inputBody = (InputBody) o;
        return getThreshold() == inputBody.getThreshold() &&
                Arrays.equals(getInputMatrix(), inputBody.getInputMatrix());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getThreshold());
        result = 31 * result + Arrays.hashCode(getInputMatrix());
        return result;
    }
}
