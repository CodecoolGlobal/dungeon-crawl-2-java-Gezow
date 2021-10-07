package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Runner extends Actor {

    public Runner(Cell cell) {
        super(cell, 5, 2);
    }

    @Override
    public String getTileName() {
        return "runner";
    }

    @Override
    public void autoMove(int frag, Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int runnerX = this.getX();
        int runnerY = this.getY();
        int dx = Direction.getRandom().getX();
        int dy = Direction.getRandom().getX();
        if (Math.abs(runnerX - playerX) < 5 && Math.abs(runnerY - playerY) < 5) {
            if (frag % 2 == 0) {
                super.enrage(playerX, playerY, runnerX, runnerY);
            }
        } else {
            if (frag % 5 == 0) {
                if (super.canMove(dx, dy)) {
                    super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
                } else autoMove(frag, player);
            }
        }
    }
}