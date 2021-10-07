package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;

public class Runner extends Actor {

    public Runner(Cell cell) {
        super(cell, Settings.RUNNER_HEALTH.getValue(), Settings.RUNNER_DAMAGE.getValue());
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
        if (Math.abs(runnerX - playerX) < Settings.RUNNER_FOLLOW_DISTANCE.getValue() && Math.abs(runnerY - playerY) < Settings.RUNNER_FOLLOW_DISTANCE.getValue()) {
            if (frag % 11 - Settings.RUNNER_RAGE_SPEED.getValue() == 0) {
                super.enrage(playerX, playerY, runnerX, runnerY);
            }
        } else {
            if (frag % 11 - Settings.RUNNER_SPEED.getValue() == 0) {
                if (super.canMove(dx, dy)) {
                    super.move(Direction.getRandom().getX(), Direction.getRandom().getY());
                } else autoMove(frag, player);
            }
        }
    }
}