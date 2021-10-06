package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Gun extends Item {
    public Gun(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        player.getInventory().getGuns().put(this.getTileName(), this);
    }

    public void shoot(){}
}
