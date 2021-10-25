package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;

public class Shield extends Consumable{

    public Shield(Cell cell) {
        super(cell, Settings.SHIELD.getValue());
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
