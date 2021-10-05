package com.codecool.dungeoncrawl.logic.items.collectibles;

public class Rocket extends Collectible{

    @Override
    public String getTileName() {
        return "rocket" + counter;
    }
}
