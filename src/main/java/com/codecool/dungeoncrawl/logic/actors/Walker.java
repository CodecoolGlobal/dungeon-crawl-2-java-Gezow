package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Walker extends Actor {

    private int health = 10;
    private int meleeDamage = 2;

    public Walker(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "walker";
    }
}
