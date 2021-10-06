package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;

import java.util.HashMap;
import java.util.LinkedList;
public class Player extends Actor {
    private final int maxHealth = 50;
    private Inventory inventory;
    private LinkedList<Bullet> bullets = new LinkedList<>();


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

    public void shoot(Direction direction){
        Bullet bullet = new Bullet(this.getCell(), direction, 5);
        bullets.add(bullet);
        // TODO: 05/10/2021 change damage when change weapons
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
