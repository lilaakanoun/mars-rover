package com.lilakanoun.marsrover;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: java -jar rover.jar input.txt");
            System.exit(1);
        }
    }
}
