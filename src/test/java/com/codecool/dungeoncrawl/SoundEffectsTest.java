package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.guns.Bfg;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundEffectsTest {
    Cell cell = new Cell(CellType.EMPTY);
    Bfg bfg = new Bfg(cell);
    SoundEffects soundEffects = new SoundEffects(bfg);

    @Test
    public void makeSound(){
        assertDoesNotThrow(() -> {
            soundEffects.run();
        });
    }

}