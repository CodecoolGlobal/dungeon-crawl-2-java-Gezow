package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.AudioFilePlayer;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.actors.Direction;

public class Pistol extends Gun{
    public Pistol(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pistol";
    }

    @Override
    public void shoot(Cell cell, Direction direction) {
        super.shoot(cell, direction, Settings.PISTOL_DAMAGE.getValue());
    }

    public void sound(){
        AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
        audioFilePlayer.play("src/main/resources/pistol.wav");
    }
}
