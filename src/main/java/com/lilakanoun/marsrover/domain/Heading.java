package com.lilakanoun.marsrover.domain;

public enum Heading {
    N, E, S, W;

    public Heading left() {
        switch (this) {
            case N:
                return W;
            case W:
                return S;
            case S:
                return E;
            default:
                return N;
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
            default:
                return N;
        }
    }
}
