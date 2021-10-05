package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bulky extends Actor {
    public Bulky(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bulky";
    }
}