package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.LinkedList;

public abstract class Gun extends Item {
    public Gun(Cell cell) {
        super(cell);
    }
    private final LinkedList<Bullet> bullets = new LinkedList<>();

    protected boolean isActive;

    @Override
    public void pickUp(Player player) {
        player.getInventory().setActiveGun(this);
        player.getInventory().getGuns().add(this);
        this.getCell().setItem(null);
    }

    public abstract void shoot(Cell cell, Direction direction);

    public void shoot(Cell cell, Direction direction, int damage){
        Bullet bullet = new Bullet(cell, direction, damage);
        bullets.add(bullet);
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public abstract void sound();
}
