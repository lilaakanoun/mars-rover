package com.lilakanoun.marsrover.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void prints_coordinates_and_heading() {
        assertThat(new Position(1, 3, Heading.N)).hasToString("1 3 N");
        assertThat(new Position(5, 1, Heading.E)).hasToString("5 1 E");
    }
}
