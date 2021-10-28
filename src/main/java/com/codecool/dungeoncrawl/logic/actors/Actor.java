package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.LinkedList;
import java.util.Objects;

public abstract class Actor implements Drawable {
    private boolean alive = true;
    private transient Cell cell;
    protected int health;
    private int meleeDamage;
    private String name;

    public Actor(Cell cell, int health, int meleeDamage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.meleeDamage = meleeDamage;
    }

    public Actor() {
    }

    public boolean canMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        return Objects.equals(nextCell.getTileName(), CellType.FLOOR.getTileName()) && nextCell.getActor() == null;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (Objects.equals(nextCell.getTileName(), CellType.FLOOR.getTileName()) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void enrage(int playerX, int playerY, int ragerX, int ragerY) {
        LinkedList<Direction> rightDirections = new LinkedList<>();
        for (Direction direction : Direction.values()
        ) {
            int dx = direction.getX();
            int dy = direction.getY();
            if (canMove(dx, dy) && (isCloserToTarget(playerX, playerY, ragerX, ragerY, dx, dy))) {
                rightDirections.add(direction);
            }
        }
        Direction randomRightDirection = rightDirections.get((int) (Math.random() * rightDirections.size()));
        int dx = randomRightDirection.getX();
        int dy = randomRightDirection.getY();
        move(dx, dy);
    }

    private boolean isCloserToTarget(int targetX, int targetY, int actorX, int actorY, int nextX, int nextY) {
        if (actorX < targetX && actorY < targetY) {
            return nextX == 1 && nextY == 1;
        } else if (actorX > targetX && actorY > targetY) {
            return nextX == -1 && nextY == -1;
        } else if (actorX < targetX && actorY > targetY) {
            return nextX == 1 && nextY == -1;
        } else if (actorX > targetX && actorY < targetY) {
            return nextX == -1 && nextY == 1;
        } else if (actorX < targetX) {
            return nextX == 1 && nextY == 0;
        } else if (actorX > targetX) {
            return nextX == -1 && nextY == 0;
        } else if (actorY < targetY) {
            return nextY == 1 && nextX == 0;
        } else if (actorY > targetY) {
            return nextY == -1 && nextX == 0;
        }
        return false;
    }

    public abstract void autoMove(int frag, Player player);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean canAttackPlayer() {
        for (Direction direction : Direction.values()) {
            try {
                if (Objects.equals(this.getCell().getNeighbor(direction.getX(), direction.getY()).getActor().getTileName(), "player")) {
                    return true;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return false;
    }

    public void attack(Actor target) {
        target.setHealth(target.getHealth() - this.meleeDamage);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
