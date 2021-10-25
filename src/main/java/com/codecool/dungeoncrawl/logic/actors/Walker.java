package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;

public class Walker extends Actor {

    public Walker(Cell cell) {
        super(cell, Settings.WALKER_HEALTH.getValue(), Settings.WALKER_DAMAGE.getValue());
    }

    @Override
    public String getTileName() {
        return "walker";
    }

    @Override
    public void autoMove(int frag, Player player) {
        if (frag % 11 - Settings.WALKER_SPEED.getValue() == 0) {
            int dx = Direction.getRandom().getX();
            int dy = Direction.getRandom().getX();
            if (super.canMove(dx, dy)) {
                super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
            } else autoMove(frag, player);
        }
    }
}
