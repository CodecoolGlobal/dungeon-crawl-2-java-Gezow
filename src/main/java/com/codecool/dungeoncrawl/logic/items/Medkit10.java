package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medkit10 extends Item{
    public Medkit10(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "medkit10";
    }
}
