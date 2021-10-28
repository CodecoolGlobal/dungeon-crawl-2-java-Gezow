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
    Shield currentItem;

    @BeforeEach
    void setUp(){
        this.map = new GameMap(10,10, CellType.FLOOR);
        this.player = new Player(map.getCell(1,2));
        this.currentItem = new Shield(map.getCell(1,2));
    }

    @Test
    void getCellForItem_returnsTrue() {
        Cell expected = map.getCell(1,2);

        Cell result = currentItem.getCell();
        assertEquals(expected, result);
    }

    @Test
    void setCellForItem_returnsTrue() {
        Cell expected = map.getCell(3,5);

        currentItem.setCell(map.getCell(3,5));
        Cell result = currentItem.getCell();
        assertEquals(expected, result);
    }

    @Test
    void getXForItem_returnsTrue() {
        int expectedX = 1;

        int result = currentItem.getX();

        assertEquals(expectedX, result);
    }

    @Test
    void getYForItem_returnsTrue() {
        int expectedY = 2;

        int result = currentItem.getY();

        assertEquals(expectedY, result);
    }
}