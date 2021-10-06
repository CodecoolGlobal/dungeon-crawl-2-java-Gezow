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
            cell.setBullet(null);
            target.setHealth(target.getHealth() - damage);
            this.setAlive(false);
            if (target.getHealth() <= 0) {
                target.setAlive(false);
                nextCell.setActor(null);
            }
        } else if (Objects.equals(nextCell.getTileName(), CellType.WALL.getTileName())) {
            this.setAlive(false);
            cell.setBullet(null);
        } else if (!Objects.equals(nextCell.getTileName(), CellType.WALL.getTileName()) && target == null) {
            cell.setBullet(null);
            nextCell.setBullet(this);
            cell = nextCell;
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
