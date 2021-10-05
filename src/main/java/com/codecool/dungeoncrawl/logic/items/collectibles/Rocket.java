package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Rocket extends Collectible{
    public Rocket(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "rocket" + counter;
    }
}
