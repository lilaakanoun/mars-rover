package com.lilakanoun.marsrover.domain;

public class Rover {

    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void execute(Instruction instruction) {
        switch (instruction) {
            case L:
                spinLeft();
                break;
            case R:
                spinRight();
                break;
            case M:
                move();
                break;
        }
    }

    private void spinLeft() {
        position = new Position(position.getX(), position.getY(), position.getHeading().left());
    }

    private void spinRight() {
        position = new Position(position.getX(), position.getY(), position.getHeading().right());
    }

    private void move() {
        int x = position.getX();
        int y = position.getY();
        Heading heading = position.getHeading();

        switch (heading) {
            case N:
                // facing north: go up
                y = y + 1;
                break;
            case S:
                // facing south: go down
                y = y - 1;
                break;
            case E:
                // facing east: go right
                x = x + 1;
                break;
            case W:
                // facing west: go left
                x = x - 1;
                break;
        }

        position = new Position(x, y, heading);
    }
}
