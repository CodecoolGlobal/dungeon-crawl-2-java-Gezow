package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.AudioFilePlayer;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Direction;

public class Bfg extends Gun{
    public Bfg(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isActive) return "bfg-active";
        else return "bfg";
    }

    @Override
    public void shoot(Cell cell, Direction direction) {
        for(Direction value: Direction.values()){
            super.shoot(cell, value, 20);
        }
    }

    public void sound(){
        AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
        audioFilePlayer.play("src/main/resources/bfg.wav");
    }
}
