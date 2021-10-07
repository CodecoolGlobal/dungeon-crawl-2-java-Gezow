package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;

public class Bulky extends Actor {

    public Bulky(Cell cell) {
        super(cell, Settings.BULKY_HEALTH.getValue(), Settings.BULKY_DAMAGE.getValue());
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
        if (Math.abs(runnerX - playerX) < Settings.BULKY_FOLLOW_DISTANCE.getValue() && Math.abs(runnerY - playerY) < Settings.BULKY_FOLLOW_DISTANCE.getValue()) {
            if (frag % 11 - Settings.BULKY_RAGE_SPEED.getValue() == 0) {
                if (this.health < Settings.BULKY_RAGE_HEALTH.getValue()) {
                    super.enrage(playerX, playerY, runnerX, runnerY);
                }
            }
        } else {
            if (frag % 11 - Settings.BULKY_SPEED.getValue() == 0) {
                if (super.canMove(dx, dy)) {
                    super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
                } else autoMove(frag, player);
            }
        }
    }
}