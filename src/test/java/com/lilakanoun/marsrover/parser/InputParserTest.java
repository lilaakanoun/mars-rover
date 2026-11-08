package com.lilakanoun.marsrover.parser;

import com.lilakanoun.marsrover.domain.Heading;
import com.lilakanoun.marsrover.domain.Plateau;
import com.lilakanoun.marsrover.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputParserTest {

    private final InputParser parser = new InputParser();

    @Test
    void parses_plateau() {
        Plateau plateau = parser.parsePlateau("5 5");

        assertThat(plateau.getUpperRightX()).isEqualTo(5);
        assertThat(plateau.getUpperRightY()).isEqualTo(5);
    }

    @Test
    void parses_position() {
        Position position = parser.parsePosition("1 2 N");

        assertThat(position.getX()).isEqualTo(1);
        assertThat(position.getY()).isEqualTo(2);
        assertThat(position.getHeading()).isEqualTo(Heading.N);
    }

    @Test
    void parses_position_with_several_digits() {
        Position position = parser.parsePosition("12 345 W");

        assertThat(position.getX()).isEqualTo(12);
        assertThat(position.getY()).isEqualTo(345);
        assertThat(position.getHeading()).isEqualTo(Heading.W);
    }
}
