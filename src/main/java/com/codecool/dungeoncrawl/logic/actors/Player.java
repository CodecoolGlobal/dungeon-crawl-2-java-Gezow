package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;

import java.util.HashMap;

public class Player extends Actor {
    private final int maxHealth = 50;
    private Inventory inventory;

    public Player(Cell cell) {
        super(cell, 10, 1);
        inventory = new Inventory(10, 50);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void setHealth(int health){
        this.health = Math.min(health, maxHealth);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
