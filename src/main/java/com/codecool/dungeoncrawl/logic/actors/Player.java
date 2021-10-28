package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.Settings;

import java.util.Objects;

public class Player extends Actor {
    private int maxHealth = Settings.PLAYER_MAX_HEALTH.getValue();
    private Inventory inventory;
    private final String name = "DoomGuy";


    public Player(Cell cell) {
        super(cell, Settings.PLAYER_MAX_HEALTH.getValue(), Settings.PLAYER_MELEE_DAMAGE.getValue());
        inventory = new Inventory(Settings.PLAYER_STARTING_AMMO.getValue(), Settings.PLAYER_MAX_AMMO.getValue());
    }

    public String getTileName() {
        if (this.isAlive()) {
            return "player";
        } else {
            return "deadplayer";
        }
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

    public void changeGun(int direction) {
        for (int i = 0; i < inventory.getGuns().size(); i++) {
            if (inventory.getGuns().get(i).isActive()) {
                if(direction == 1) {
                    if (i == inventory.getGuns().size() - 1) {
                        inventory.setActiveGun(inventory.getGuns().get(0));
                    } else {
                        inventory.setActiveGun(inventory.getGuns().get(i + direction));
                    }
                }
                else{
                    if (i == 0){
                        inventory.setActiveGun(inventory.getGuns().get(inventory.getGuns().size() - 1));
                    }
                    else{
                        inventory.setActiveGun(inventory.getGuns().get(i + direction));
                    }
                }
                break;
            }
        }
    }

    public void shoot(Direction direction) {
        if(inventory.getAmmo() > 0) {
            inventory.getActiveGun().shoot(this.getCell(), direction);
            inventory.setAmmo(inventory.getAmmo() - 1);
        }
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
        if (Objects.equals(nextCell.getTileName(), CellType.FLOOR.getTileName()) && nextCell.getActor() == null) {
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