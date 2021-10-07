package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.AudioFilePlayer;
import com.codecool.dungeoncrawl.logic.Cell;

public class Pistol extends Gun{
    public Pistol(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pistol";
    }

    public void sound(){
        AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
        audioFilePlayer.play("src/main/resources/pistol.wav");
    }
}
