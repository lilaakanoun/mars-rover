package com.lilakanoun.marsrover;

import com.lilakanoun.marsrover.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MissionRunnerTest {

    @Test
    void runs_rovers_one_after_the_other() {
        List<String> lines = List.of("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM");

        List<Position> finalPositions = new MissionRunner().run(lines);

        assertThat(finalPositions).hasSize(2);
        assertThat(finalPositions.get(0)).hasToString("1 3 N");
        assertThat(finalPositions.get(1)).hasToString("5 1 E");
    }
}
