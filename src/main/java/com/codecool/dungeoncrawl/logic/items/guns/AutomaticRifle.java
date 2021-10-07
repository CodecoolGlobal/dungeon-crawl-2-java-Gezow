package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.AudioFilePlayer;
import com.codecool.dungeoncrawl.logic.Cell;

public class AutomaticRifle extends Gun{
    public AutomaticRifle(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "automaticrifle";
    }

    public void sound(){
        AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
        audioFilePlayer.play("src/main/resources/automaticrifle.wav");
    }
}
