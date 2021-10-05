package com.codecool.dungeoncrawl;

public class MusicPlayer implements Runnable{
    private AudioFilePlayer audioFilePlayer;

    public MusicPlayer(AudioFilePlayer audioFilePlayer) {
        this.audioFilePlayer = audioFilePlayer;
    }

    @Override
    public void run() {
        while(true) {
            audioFilePlayer.play("/home/troll/TroLL_Codes/OOP/third_week/dungeon-crawl-1-java-Gezow/src/main/resources/Doom.wav");
        }
    }
}
