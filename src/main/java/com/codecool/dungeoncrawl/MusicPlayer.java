package com.codecool.dungeoncrawl;

public class MusicPlayer implements Runnable{
    private AudioFilePlayer audioFilePlayer;
    private boolean map;

    public MusicPlayer(AudioFilePlayer audioFilePlayer) {
        this.audioFilePlayer = audioFilePlayer;
        this.map = true;
    }

    @Override
    public void run() {
        while(map) {
            audioFilePlayer.play("src/main/resources/Doom.wav");
        }
    }

    public void setMap(boolean map) {
        this.map = map;
    }
}
