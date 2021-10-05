package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Collectible extends Item {
    protected int counter = 0;

    public Collectible(Cell cell) {
        super(cell);
    }
}
