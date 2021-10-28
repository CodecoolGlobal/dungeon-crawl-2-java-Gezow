package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Collectible extends Item {
    protected int counter = 0;
    protected transient static int rocketParts = 0;

    @Override
    public void pickUp(Player player){
        if(this instanceof Rocket) player.getInventory().getRockets().add((Rocket) this);
        else player.getInventory().getCollectibles().add(this);
        counter = player.getInventory().getCollectibles().size();
        this.getCell().setItem(null);
    }

    public Collectible(Cell cell) {
        super(cell);
    }
}
