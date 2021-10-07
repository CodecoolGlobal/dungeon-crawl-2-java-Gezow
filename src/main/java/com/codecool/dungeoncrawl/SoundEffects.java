package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.items.guns.Gun;

public class SoundEffects implements Runnable{
    private Gun gun;

    public SoundEffects(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void run() {
        gun.sound();
    }
}