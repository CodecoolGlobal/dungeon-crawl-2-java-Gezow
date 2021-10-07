package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.AudioFilePlayer;
import com.codecool.dungeoncrawl.logic.Cell;

public class Bfg extends Gun{
    public Bfg(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bfg";
    }

    public void sound(){
        AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
        audioFilePlayer.play("src/main/resources/bfg.wav");
    }
}
