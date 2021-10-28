package com.codecool.dungeoncrawl;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AudioFilePlayerTest {
    AudioFilePlayer audioFilePlayer = new AudioFilePlayer();

    @Test
    public void fileNotFound() {
        assertThrows(IllegalStateException.class, () -> {
            audioFilePlayer.play("src/main/resources/test.wav");
        });
    }

    @Test
    public void canPlay(){
        assertDoesNotThrow(()-> {
            audioFilePlayer.play("src/main/resources/bfg.wav");
        });
    }

}