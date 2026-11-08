package com.lilakanoun.marsrover.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InstructionTest {

    @Test
    void reads_letter() {
        assertThat(Instruction.fromLetter('L')).isEqualTo(Instruction.L);
        assertThat(Instruction.fromLetter('R')).isEqualTo(Instruction.R);
        assertThat(Instruction.fromLetter('M')).isEqualTo(Instruction.M);
    }

    @Test
    void rejects_unknown_letter() {
        assertThatThrownBy(() -> Instruction.fromLetter('X'))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
