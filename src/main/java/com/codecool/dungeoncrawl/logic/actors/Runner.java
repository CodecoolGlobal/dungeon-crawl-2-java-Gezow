package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Runner extends Actor {

    private int health = 5;
    private int meleeDamage = 1;

    public Runner(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "runner";
    }
}