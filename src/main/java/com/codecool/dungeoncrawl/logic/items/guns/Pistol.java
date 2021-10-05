package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;

public class Pistol extends Gun{
    public Pistol(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pistol";
    }
}
