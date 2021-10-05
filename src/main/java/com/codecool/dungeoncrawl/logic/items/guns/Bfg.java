package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bfg extends Gun{
    public Bfg(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bfg";
    }
}
