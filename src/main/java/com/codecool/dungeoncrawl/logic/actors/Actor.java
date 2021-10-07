package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Objects;

public abstract class Actor implements Drawable {
    private boolean alive = true;
    private Cell cell;
    protected int health;
    private int meleeDamage;

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

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }

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

    public boolean canAttackPlayer(){
        for (Direction direction : Direction.values()) {
            try {
                if (Objects.equals(this.getCell().getNeighbor(direction.getX(), direction.getY()).getActor().getTileName(), "player")) {
                    return true;
                }
            }catch (NullPointerException ignored){
            }
        }
        return false;
    }

    public void attack(Actor target){
        target.setHealth(target.getHealth()-this.meleeDamage);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
