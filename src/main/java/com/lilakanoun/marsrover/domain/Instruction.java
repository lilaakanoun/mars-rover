package com.lilakanoun.marsrover.domain;

public enum Instruction {
    L, R, M;

    public static Instruction fromLetter(char letter) {
        switch (letter) {
            case 'L':
                return L;
            case 'R':
                return R;
            case 'M':
                return M;
            default:
                throw new IllegalArgumentException("unknown instruction: " + letter);
        }
    }
}
