package com.lilakanoun.marsrover.domain;

public class Position {

    private final int x;
    private final int y;
    private final Heading heading;

    public Position(int x, int y, Heading heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Heading getHeading() {
        return heading;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + heading;
    }
}
