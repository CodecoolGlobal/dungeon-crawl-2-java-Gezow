package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Objects;

public class Bullet extends Actor {
    private Cell cell;
    private Direction direction;
    private int damage;


    public Bullet(Cell cell, Direction direction, int damage) {
        super();
        this.cell = cell;
        this.direction = direction;
        this.damage = damage;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor target = nextCell.getActor();
        if (nextCell.hasActor()) {
            deleteBulletTrace();
            target.setHealth(target.getHealth() - damage);
            this.setAlive(false);
            if (target.getHealth() <= 0) {
                target.setAlive(false);
                nextCell.setActor(null);
            }
        } else if (Objects.equals(nextCell.getTileName(), CellType.WALL.getTileName())) {
            this.setAlive(false);
            deleteBulletTrace();
        } else if (!Objects.equals(nextCell.getTileName(), CellType.WALL.getTileName()) && target == null) {
            deleteBulletTrace();
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void deleteBulletTrace() {
        try {
            if (Objects.equals(this.cell.getActor().getTileName(), "bullet")) {
                this.cell.setActor(null);
            }
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public String getTileName() {
        return "bullet";
    }

    public Direction getDirection() {
        return direction;
    }
}
