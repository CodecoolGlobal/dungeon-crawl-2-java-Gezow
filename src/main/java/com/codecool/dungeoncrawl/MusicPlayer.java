package com.codecool.dungeoncrawl;

public class MusicPlayer implements Runnable{
    private AudioFilePlayer audioFilePlayer;

    public MusicPlayer(AudioFilePlayer audioFilePlayer) {
        this.audioFilePlayer = audioFilePlayer;
    }

    @Override
    public void run() {
        while(true) {
            audioFilePlayer.play("src/main/resources/Doom.wav");
        }
    }
}
