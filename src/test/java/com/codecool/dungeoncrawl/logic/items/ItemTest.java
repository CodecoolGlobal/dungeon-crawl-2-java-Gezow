package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.consumables.Ammo;
import com.codecool.dungeoncrawl.logic.items.consumables.Shield;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    GameMap map;
    Player player;
    Inventory expectedInventory;

    @BeforeEach
    void setUp(){
        this.map = new GameMap(10,10, CellType.FLOOR);
        this.player = new Player(map.getCell(1,2));
        this.expectedInventory = new Inventory(10,50);
    }

    @Test
    void getCell() {
        Cell expected = map.getCell(1,2);

        Pistol pistol = new Pistol(map.getCell(1,2));
        Cell result = pistol.getCell();
        assertEquals(expected, result);
    }

    @Test
    void setCell() {
        Cell expected = map.getCell(3,5);

        Ammo ammo = new Ammo(map.getCell(0,0));
        ammo.setCell(map.getCell(3,5));
        Cell result = ammo.getCell();
        assertEquals(expected, result);
    }

    @Test
    void getX() {
        int expectedX = 3;

        Shield shield = new Shield(map.getCell(3,2));
        int result = shield.getX();

        assertEquals(expectedX, result);
    }

    @Test
    void getY() {
        int expectedY = 2;

        Shield shield = new Shield(map.getCell(3,2));
        int result = shield.getY();

        assertEquals(expectedY, result);
    }
}