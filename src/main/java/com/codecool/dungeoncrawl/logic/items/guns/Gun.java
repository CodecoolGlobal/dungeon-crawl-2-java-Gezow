package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Gun extends Item {
    public Gun(Cell cell) {
        super(cell);
    }

    public void shoot(){}
}
