package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bulky extends Actor {

    public Bulky(Cell cell) {
        super(cell, 30, 5);
    }

    @Override
    public String getTileName() {
        return "bulky";
    }

    @Override
    public void autoMove(int frag, Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int runnerX = this.getX();
        int runnerY = this.getY();
        int dx = Direction.getRandom().getX();
        int dy = Direction.getRandom().getX();
        if (Math.abs(runnerX - playerX) < 10 && Math.abs(runnerY - playerY) < 10) {
            if (frag % 4 == 0) {
                if (this.health < 20) {
                    super.enrage(playerX, playerY, runnerX, runnerY);
                }
            }
        } else {
            if (frag % 10 == 0) {
                if (super.canMove(dx, dy)) {
                    super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
                } else autoMove(frag, player);
            }
        }
    }
}