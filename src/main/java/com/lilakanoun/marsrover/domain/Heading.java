package com.lilakanoun.marsrover.domain;

public enum Heading {
    N, E, S, W;

    public static Heading fromLetter(char letter) {
        switch (letter) {
            case 'N':
                return N;
            case 'E':
                return E;
            case 'S':
                return S;
            case 'W':
                return W;
            default:
                throw new IllegalArgumentException("unknown heading: " + letter);
        }
    }

    public Heading left() {
        switch (this) {
            case N:
                return W;
            case W:
                return S;
            case S:
                return E;
            case E:
                return N;
            default:
                throw new IllegalStateException("unknown heading: " + this);
        }
    }

    public Heading right() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                throw new IllegalStateException("unknown heading: " + this);
        }
    }
}
