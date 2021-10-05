package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medkit50 extends Item{
    public Medkit50(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Medkit50";
    }
}
