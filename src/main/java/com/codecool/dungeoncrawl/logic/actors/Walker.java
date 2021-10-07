package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Walker extends Actor {

    public Walker(Cell cell) {
        super(cell, 10, 2);
    }

    @Override
    public String getTileName() {
        return "walker";
    }

    @Override
    public void autoMove(int frag, Player player) {
        if (frag % 5 == 0) {
            int dx = Direction.getRandom().getX();
            int dy = Direction.getRandom().getX();
            if (super.canMove(dx, dy)) {
                super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
            } else autoMove(frag, player);
        }
    }
}
