package com.lilakanoun.marsrover.domain;

public class Plateau {

    private final int upperRightX;
    private final int upperRightY;

    public Plateau(int upperRightX, int upperRightY) {
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }

    public int getUpperRightX() {
        return upperRightX;
    }

    public int getUpperRightY() {
        return upperRightY;
    }
}
