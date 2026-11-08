package com.lilakanoun.marsrover.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HeadingTest {

    @Test
    void spins_left() {
        assertThat(Heading.N.left()).isEqualTo(Heading.W);
        assertThat(Heading.W.left()).isEqualTo(Heading.S);
        assertThat(Heading.S.left()).isEqualTo(Heading.E);
        assertThat(Heading.E.left()).isEqualTo(Heading.N);
    }

    @Test
    void spins_right() {
        assertThat(Heading.N.right()).isEqualTo(Heading.E);
        assertThat(Heading.E.right()).isEqualTo(Heading.S);
        assertThat(Heading.S.right()).isEqualTo(Heading.W);
        assertThat(Heading.W.right()).isEqualTo(Heading.N);
    }

    @Test
    void reads_letter() {
        assertThat(Heading.fromLetter('N')).isEqualTo(Heading.N);
        assertThat(Heading.fromLetter('E')).isEqualTo(Heading.E);
        assertThat(Heading.fromLetter('S')).isEqualTo(Heading.S);
        assertThat(Heading.fromLetter('W')).isEqualTo(Heading.W);
    }

    @Test
    void rejects_unknown_letter() {
        assertThatThrownBy(() -> Heading.fromLetter('X'))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
