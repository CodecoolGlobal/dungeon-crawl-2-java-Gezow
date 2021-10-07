package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.items.Item;

public class HealthPackSmall extends Consumable {
    public HealthPackSmall(Cell cell) {
        super(cell, Settings.HEALTH_PACK_SMALL.getValue());
    }

    @Override
    public String getTileName() {
        return "medkit10";
    }
}
