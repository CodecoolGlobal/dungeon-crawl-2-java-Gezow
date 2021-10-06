package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Consumable extends Item {
    protected final int value;

    public Consumable(Cell cell, int value) {
        super(cell);
        this.value = value;
    }

    @Override
    public void pickUp(Player player) {
        if(this instanceof Ammo){
            player.getInventory().setAmmo(player.getInventory().getAmmo() + this.value);
        }
        else{
            player.setHealth(player.getHealth() + this.value);
        }
    }
}
