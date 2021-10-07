package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Rocket extends Collectible{
    private int rocketSegment;
    public Rocket(Cell cell) {
        super(cell);
        rocketSegment = rocketParts;
        rocketParts++;
    }

    @Override
    public String getTileName() {
        return "rocket" + rocketSegment;
    }
}
