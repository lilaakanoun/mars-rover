package com.lilakanoun.marsrover.api;

import com.lilakanoun.marsrover.domain.Plateau;
import com.lilakanoun.marsrover.domain.Position;

import java.util.List;

public class MissionResponse {

    private final Plateau plateau;
    private final List<Position> rovers;

    public MissionResponse(Plateau plateau, List<Position> rovers) {
        this.plateau = plateau;
        this.rovers = rovers;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public List<Position> getRovers() {
        return rovers;
    }
}
