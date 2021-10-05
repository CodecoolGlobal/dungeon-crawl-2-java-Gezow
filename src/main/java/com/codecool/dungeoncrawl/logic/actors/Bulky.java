package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bulky extends Actor {

    private int health = 30;
    private int meleeDamage = 5;

    public Bulky(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bulky";
    }
}