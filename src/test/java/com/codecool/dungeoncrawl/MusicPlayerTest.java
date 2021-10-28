package com.codecool.dungeoncrawl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {
    AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
    MusicPlayer musicPlayer = new MusicPlayer(audioFilePlayer);

    @Test
    public void setMapBoolean(){
        boolean expected = false;
        musicPlayer.setMap(false);
        assertEquals(expected, musicPlayer.isMap());
    }

}