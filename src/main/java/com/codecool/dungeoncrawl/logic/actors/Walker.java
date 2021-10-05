package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Walker extends Actor {
    public Walker(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "walker";
    }
}
