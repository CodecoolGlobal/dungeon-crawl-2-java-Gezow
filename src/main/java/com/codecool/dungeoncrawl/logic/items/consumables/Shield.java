package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Consumable{

    public Shield(Cell cell) {
        super(cell, 50);
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
