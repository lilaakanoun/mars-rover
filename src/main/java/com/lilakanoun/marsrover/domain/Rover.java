package com.lilakanoun.marsrover.domain;

public class Rover {

    private int x;
    private int y;
    private Heading heading;

    public Rover(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.heading = position.getHeading();
    }

    public Position getPosition() {
        return new Position(x, y, heading);
    }

    public void execute(String instructions) {
        for (int i = 0; i < instructions.length(); i++) {
            apply(Instruction.fromLetter(instructions.charAt(i)));
        }
    }

    private void apply(Instruction instruction) {
        switch (instruction) {
            case L:
                heading = heading.left();
                break;
            case R:
                heading = heading.right();
                break;
            case M:
                move();
                break;
            default:
                throw new IllegalStateException("unknown instruction: " + instruction);
        }
    }

    private void move() {
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
            default:
                throw new IllegalStateException("unknown heading: " + heading);
        }
    }
}
