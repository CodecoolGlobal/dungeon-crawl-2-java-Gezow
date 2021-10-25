package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Crystal extends Collectible{
    public Crystal(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "crystal";
    }
}
