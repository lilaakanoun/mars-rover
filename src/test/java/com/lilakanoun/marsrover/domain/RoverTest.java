package com.lilakanoun.marsrover.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoverTest {

    @Test
    void spins_without_moving() {
        Rover rover = new Rover(new Position(1, 2, Heading.N));

        rover.execute("L");
        assertThat(rover.getPosition()).hasToString("1 2 W");

        rover.execute("RR");
        assertThat(rover.getPosition()).hasToString("1 2 E");
    }

    @Test
    void moves_forward() {
        Rover north = new Rover(new Position(1, 1, Heading.N));
        Rover south = new Rover(new Position(1, 1, Heading.S));
        Rover east = new Rover(new Position(1, 1, Heading.E));
        Rover west = new Rover(new Position(1, 1, Heading.W));

        north.execute("M");
        south.execute("M");
        east.execute("M");
        west.execute("M");

        assertThat(north.getPosition()).hasToString("1 2 N");
        assertThat(south.getPosition()).hasToString("1 0 S");
        assertThat(east.getPosition()).hasToString("2 1 E");
        assertThat(west.getPosition()).hasToString("0 1 W");
    }

    @Test
    void executes_the_test_input() {
        Rover first = new Rover(new Position(1, 2, Heading.N));
        Rover second = new Rover(new Position(3, 3, Heading.E));

        first.execute("LMLMLMLMM");
        second.execute("MMRMMRMRRM");

        assertThat(first.getPosition()).hasToString("1 3 N");
        assertThat(second.getPosition()).hasToString("5 1 E");
    }

    @Test
    void rejects_unknown_instruction() {
        Rover rover = new Rover(new Position(1, 2, Heading.N));

        assertThatThrownBy(() -> rover.execute("X"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
