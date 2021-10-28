package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.collectibles.Key;
import com.codecool.dungeoncrawl.logic.items.consumables.Ammo;
import com.codecool.dungeoncrawl.logic.items.consumables.Shield;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    GameMap map;
    Player player;

    @BeforeEach
    void setUp(){
        this.map = new GameMap(10,10, CellType.FLOOR);
        this.player = new Player(map.getCell(1,2));
    }

    @Test
    void getCellForItem_returnsTrue() {
        Cell expected = map.getCell(1,2);

        Shield shield = new Shield(map.getCell(1,2));
        Cell result = shield.getCell();

        assertEquals(expected, result);
    }

    @Test
    void setCellForItem_returnsTrue() {
        Cell expected = map.getCell(3,5);

        Ammo ammo = new Ammo(map.getCell(1,2));
        ammo.setCell(map.getCell(3,5));
        Cell result = ammo.getCell();
        assertEquals(expected, result);
    }

    @Test
    void getXForItem_returnsTrue() {
        int expectedX = 1;

        Pistol pistol = new Pistol(map.getCell(1,2));
        int result = pistol.getX();

        assertEquals(expectedX, result);
    }

    @Test
    void getYForItem_returnsTrue() {
        int expectedY = 2;

        Key key = new Key(map.getCell(1,2));
        int result = key.getY();

        assertEquals(expectedY, result);
    }
}