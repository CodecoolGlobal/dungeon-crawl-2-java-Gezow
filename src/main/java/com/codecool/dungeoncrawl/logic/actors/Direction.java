package com.codecool.dungeoncrawl.logic.actors;

public enum Direction {
    NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public static Direction getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}