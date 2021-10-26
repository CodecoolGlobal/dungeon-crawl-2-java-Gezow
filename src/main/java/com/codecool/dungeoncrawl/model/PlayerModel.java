package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private int x;
    private int y;
    private int maxHealth;
    protected int health;
    private Inventory inventory;

    public PlayerModel(Player player) {
        this.x = player.getX();
        this.y = player.getY();

        this.maxHealth = player.getMaxHealth();
        this.health = player.getHealth();

        this.inventory = player.getInventory();
    }


    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
