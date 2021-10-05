package com.codecool.dungeoncrawl.logic.actors;

public enum Direction {
    NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);

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