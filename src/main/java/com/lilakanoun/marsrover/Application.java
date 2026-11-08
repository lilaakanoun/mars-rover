package com.lilakanoun.marsrover;

import com.lilakanoun.marsrover.domain.Rover;
import com.lilakanoun.marsrover.parser.InputParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java -jar rover.jar input.txt");
            System.exit(1);
        }

        List<String> lines = Files.readAllLines(Path.of(args[0]));
        InputParser parser = new InputParser();
        StringBuilder output = new StringBuilder();

        // two lines per rover: position, then instructions
        for (int i = 1; i + 1 < lines.size(); i = i + 2) {
            Rover rover = new Rover(parser.parsePosition(lines.get(i)));
            rover.execute(lines.get(i + 1));
            output.append(rover.getPosition()).append('\n');
        }

        System.out.print(output);
    }
}
