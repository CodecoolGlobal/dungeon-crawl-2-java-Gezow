package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Medkit10 extends Consumable {
    public Medkit10(Cell cell) {
        super(cell, 10);
    }

    @Override
    public String getTileName() {
        return "medkit10";
    }
}
