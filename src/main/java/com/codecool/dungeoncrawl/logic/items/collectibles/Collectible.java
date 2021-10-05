package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Collectible extends Item {
    protected int counter = 0;

    @Override
    public void pickUp(Player player){
        player.getCollectibles().put(this.getTileName(), this);
        counter = player.getCollectibles().size();
    }

    public Collectible(Cell cell) {
        super(cell);
    }
}
