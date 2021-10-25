package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.items.Item;

public class HealthPackBig extends Consumable {
    public HealthPackBig(Cell cell) {
        super(cell, Settings.HEALTH_PACK_BIG.getValue());
    }

    @Override
    public String getTileName() {
        return "medkit50";
    }
}
