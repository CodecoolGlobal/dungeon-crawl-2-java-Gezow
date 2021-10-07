package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.Settings;

import java.util.Objects;

public class Player extends Actor{
    private int maxHealth = Settings.PLAYER_MAX_HEALTH.getValue();
    private Inventory inventory;


    public Player(Cell cell) {
        super(cell, Settings.PLAYER_MAX_HEALTH.getValue(), Settings.PLAYER_MELEE_DAMAGE.getValue());
        inventory = new Inventory(Settings.PLAYER_STARTING_AMMO.getValue(), Settings.PLAYER_MAX_AMMO.getValue());
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void autoMove(int frag, Player player) {

    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void shoot(Direction direction) {
        inventory.getActiveGun().shoot(this.getCell(), direction);
        // TODO: 05/10/2021 change damage when change weapons
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(Objects.equals(nextCell.getTileName(), CellType.FLOOR.getTileName()) && nextCell.getActor() == null) {
            this.getCell().setActor(null);
            nextCell.setActor(this);
            this.setCell(nextCell);
        }
    }

    @Override
    public Cell getCell() {
        return super.getCell();
    }

    @Override
    public void setCell(Cell cell) {
        super.setCell(cell);
    }
}