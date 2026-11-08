package com.lilakanoun.marsrover;

import com.lilakanoun.marsrover.domain.Position;
import com.lilakanoun.marsrover.domain.Rover;
import com.lilakanoun.marsrover.parser.InputParser;

import java.util.ArrayList;
import java.util.List;

public class MissionRunner {

    private final InputParser parser = new InputParser();

    public List<Position> run(List<String> lines) {
        List<Position> finalPositions = new ArrayList<>();

        // two lines per rover: position, then instructions
        for (int i = 1; i + 1 < lines.size(); i = i + 2) {
            Rover rover = new Rover(parser.parsePosition(lines.get(i)));
            rover.execute(lines.get(i + 1));
            finalPositions.add(rover.getPosition());
        }

        return finalPositions;
    }
}
