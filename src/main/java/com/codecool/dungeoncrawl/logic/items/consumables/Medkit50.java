package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Medkit50 extends Consumable {
    public Medkit50(Cell cell) {
        super(cell, 50);
    }

    @Override
    public String getTileName() {
        return "Medkit50";
    }
}
