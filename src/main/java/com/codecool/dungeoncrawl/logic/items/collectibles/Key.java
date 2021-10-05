package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Collectible{
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
