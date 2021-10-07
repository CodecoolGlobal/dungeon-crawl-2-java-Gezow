package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    FLAME("flame"),
    DOOR("door"),
    PORTAL("portal"),
    FIRE("fire"),
    HELLCRYSTAL("hellcrystal");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
