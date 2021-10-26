package com.codecool.dungeoncrawl.logic.items.collectibles;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectibleTest {
    GameMap map;
    Player player;
    Collectible expectedCollectible;
    Inventory expectedInventory;

    @BeforeEach
    void setUp(){
        this.map = new GameMap(10,10, CellType.FLOOR);
        this.player = new Player(map.getCell(1,2));
        this.expectedInventory = new Inventory(10,50);
    }

    @Test
    void playerPicksUpKey_returnTrue() {
//        this.expectedCollectible = new Key(map.getCell(1,2));
//        String expected = expectedCollectible.toString();
        Key key = new Key(map.getCell(1,2));
        expectedInventory.getCollectibles().add(key);
        String expected = expectedInventory.getCollectibles().toString();

        key.pickUp(player);
        String result = player.getInventory().getCollectibles().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpCrystal_returnTrue() {
        Crystal crystal = new Crystal(map.getCell(1,2));
        expectedInventory.getCollectibles().add(crystal);
        String expected = expectedInventory.getCollectibles().toString();

        crystal.pickUp(player);
        String result = player.getInventory().getCollectibles().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpRocket_returnTrue() {
        Rocket rocket = new Rocket(map.getCell(1,2));
        expectedInventory.getCollectibles().add(rocket);
        String expected = expectedInventory.getCollectibles().toString();

        rocket.pickUp(player);
        String result = player.getInventory().getRockets().toString();

        assertEquals(expected, result);
    }

    @Test
    void removeKeyFromCollectibles_returnTrue() {
        String expected = expectedInventory.getCollectibles().toString();

        Key key = new Key(map.getCell(2,3));
        key.pickUp(player);
        player.getInventory().getCollectibles().remove(key);
        String result = player.getInventory().getCollectibles().toString();

        assertEquals(expected, result);
    }

    @Test
    void removeCrystalFromCollectibles_returnTrue() {
        String expected = expectedInventory.getCollectibles().toString();

        Crystal crystal = new Crystal(map.getCell(2,3));
        crystal.pickUp(player);
        player.getInventory().getCollectibles().remove(crystal);
        String result = player.getInventory().getCollectibles().toString();

        assertEquals(expected, result);
    }

    @Test
    void removeRocketFromCollectibles_returnTrue() {
        String expected = expectedInventory.getRockets().toString();

        Rocket rocket = new Rocket(map.getCell(2,3));
        rocket.pickUp(player);
        player.getInventory().getRockets().remove(rocket);
        String result = player.getInventory().getRockets().toString();

        assertEquals(expected, result);
    }
}