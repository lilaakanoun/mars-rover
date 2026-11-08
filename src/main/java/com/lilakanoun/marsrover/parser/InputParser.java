package com.lilakanoun.marsrover.parser;

import com.lilakanoun.marsrover.domain.Heading;
import com.lilakanoun.marsrover.domain.Plateau;
import com.lilakanoun.marsrover.domain.Position;

public class InputParser {

    public Plateau parsePlateau(String line) {
        int space = line.indexOf(' ');
        int x = Integer.parseInt(line, 0, space, 10);
        int y = Integer.parseInt(line, space + 1, line.length(), 10);
        return new Plateau(x, y);
    }

    public Position parsePosition(String line) {
        int firstSpace = line.indexOf(' ');
        int secondSpace = line.indexOf(' ', firstSpace + 1);
        int x = Integer.parseInt(line, 0, firstSpace, 10);
        int y = Integer.parseInt(line, firstSpace + 1, secondSpace, 10);
        Heading heading = Heading.fromLetter(line.charAt(secondSpace + 1));
        return new Position(x, y, heading);
    }
}
