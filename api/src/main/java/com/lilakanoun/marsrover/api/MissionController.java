package com.lilakanoun.marsrover.api;

import com.lilakanoun.marsrover.MissionRunner;
import com.lilakanoun.marsrover.domain.Plateau;
import com.lilakanoun.marsrover.domain.Position;
import com.lilakanoun.marsrover.parser.InputParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MissionController {

    private final InputParser parser = new InputParser();
    private final MissionRunner missionRunner = new MissionRunner();

    @PostMapping(value = "/missions", consumes = MediaType.TEXT_PLAIN_VALUE)
    public MissionResponse run(@RequestBody String input) {
        List<String> lines = input.lines().collect(Collectors.toList());

        Plateau plateau = parser.parsePlateau(lines.get(0));
        List<Position> rovers = missionRunner.run(lines);

        return new MissionResponse(plateau, rovers);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badInput(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
