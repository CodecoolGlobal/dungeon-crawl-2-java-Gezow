package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ammo extends Item{
    public Ammo(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ammo";
    }
}
