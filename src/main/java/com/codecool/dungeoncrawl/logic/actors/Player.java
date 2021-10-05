package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;

import java.util.HashMap;

public class Player extends Actor {

    private final int maxHealth = 50;
    private int ammo;
    private int maxAmmo;
    private HashMap<String, Gun> guns = new HashMap<>();
    private HashMap<String, Collectible> collectibles = new HashMap<>();
  
    public Player(Cell cell) {
        super(cell, 10, 1);
        ammo = 10;
        maxAmmo = 50;
    }

    public String getTileName() {
        return "player";
    }

    public HashMap<String, Gun> getGuns() {
        return guns;
    }

    public void setGuns(HashMap<String, Gun> guns) {
        this.guns = guns;
    }

    public HashMap<String, Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(HashMap<String, Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = Math.min(ammo, maxAmmo);
    }

    @Override
    public void setHealth(int health){
        this.health = Math.min(health, maxHealth);
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }
}
