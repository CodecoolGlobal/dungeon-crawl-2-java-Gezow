package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Ammo extends Consumable {
    public Ammo(Cell cell) {
        super(cell, Settings.AMMO_PACK.getValue());
    }

    @Override
    public String getTileName() {
        return "ammo";
    }
}
