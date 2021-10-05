package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shotgun extends Gun{
    public Shotgun(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "shotgun";
    }
}
