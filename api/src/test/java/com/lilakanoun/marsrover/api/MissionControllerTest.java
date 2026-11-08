package com.lilakanoun.marsrover.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MissionController.class)
class MissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returns_final_position_of_each_rover() throws Exception {
        String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n";

        mockMvc.perform(post("/missions").contentType(MediaType.TEXT_PLAIN).content(input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plateau.upperRightX").value(5))
                .andExpect(jsonPath("$.plateau.upperRightY").value(5))
                .andExpect(jsonPath("$.rovers[0].x").value(1))
                .andExpect(jsonPath("$.rovers[0].y").value(3))
                .andExpect(jsonPath("$.rovers[0].heading").value("N"))
                .andExpect(jsonPath("$.rovers[1].x").value(5))
                .andExpect(jsonPath("$.rovers[1].y").value(1))
                .andExpect(jsonPath("$.rovers[1].heading").value("E"));
    }

    @Test
    void rejects_unknown_instruction() throws Exception {
        String input = "5 5\n1 2 N\nLMX\n";

        mockMvc.perform(post("/missions").contentType(MediaType.TEXT_PLAIN).content(input))
                .andExpect(status().isBadRequest());
    }
}
