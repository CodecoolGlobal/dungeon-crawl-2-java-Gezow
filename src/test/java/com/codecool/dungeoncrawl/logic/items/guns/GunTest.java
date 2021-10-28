package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GunTest {
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
    void playerPicksUpAutomaticRifle_returnsTrue() {
        AutomaticRifle ar = new AutomaticRifle(map.getCell(2,3));
        expectedInventory.getGuns().add(ar);
        String expected = expectedInventory.getGuns().toString();

        ar.pickUp(player);
        String result = player.getInventory().getGuns().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpBfg_returnsTrue() {
        Bfg bfg = new Bfg(map.getCell(2,3));
        expectedInventory.getGuns().add(bfg);
        String expected = expectedInventory.getGuns().toString();

        bfg.pickUp(player);
        String result = player.getInventory().getGuns().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpPistol_returnsTrue() {
        Pistol pistol = new Pistol(map.getCell(2,3));
        expectedInventory.getGuns().add(pistol);
        String expected = expectedInventory.getGuns().toString();

        pistol.pickUp(player);
        String result = player.getInventory().getGuns().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpShotgun_returnsTrue() {
        Shotgun shotgun = new Shotgun(map.getCell(2,3));
        expectedInventory.getGuns().add(shotgun);
        String expected = expectedInventory.getGuns().toString();

        shotgun.pickUp(player);
        String result = player.getInventory().getGuns().toString();

        assertEquals(expected, result);
    }

    @Test
    void playerSelectGun_returnsTrue() {
        Bfg bfg = new Bfg(map.getCell(1,2));
        Pistol pistol = new Pistol(map.getCell(2,3));
        expectedInventory.getGuns().add(bfg);
        expectedInventory.getGuns().add(pistol);
        expectedInventory.setActiveGun(bfg);
        String expected = expectedInventory.getActiveGun().toString();

        bfg.pickUp(player);
        pistol.pickUp(player);
        player.changeGun(-1);
        String result = player.getInventory().getActiveGun().toString();

        assertEquals(expected, result);
    }
}