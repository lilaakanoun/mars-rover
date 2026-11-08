package com.lilakanoun.marsrover;

import com.lilakanoun.marsrover.domain.Position;

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
        StringBuilder output = new StringBuilder();

        for (Position position : new MissionRunner().run(lines)) {
            output.append(position).append('\n');
        }

        System.out.print(output);
    }
}
