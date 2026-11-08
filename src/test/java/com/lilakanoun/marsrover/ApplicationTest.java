package com.lilakanoun.marsrover;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void captureOutput() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    @Test
    void prints_final_position_of_each_rover() throws Exception {
        Application.main(new String[]{"src/test/resources/input.txt"});

        assertThat(output.toString()).isEqualTo("1 3 N\n5 1 E\n");
    }
}
