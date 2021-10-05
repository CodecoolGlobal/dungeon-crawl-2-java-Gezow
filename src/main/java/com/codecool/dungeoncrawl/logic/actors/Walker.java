package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Walker extends Actor {

    public Walker(Cell cell) {
        super(cell, 10, 2);
    }

    @Override
    public String getTileName() {
        return "walker";
    }
}
