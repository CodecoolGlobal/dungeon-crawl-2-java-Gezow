package com.codecool.dungeoncrawl.logic.items.consumables;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumableTest {
    GameMap map;
    Player player;

    @BeforeEach
    void setUp(){
        this.map = new GameMap(10,10, CellType.FLOOR);
        this.player = new Player(map.getCell(2,1));
    }

    @Test
    void constructorGetNoArgument_throwNullPointerException (){
        assertThrows(NullPointerException.class, () ->{
            Ammo ammo = new Ammo(null);
        });
        assertThrows(NullPointerException.class, () -> {
            HealthPackBig healthPackBig = new HealthPackBig(null);
        });
        assertThrows(NullPointerException.class, () -> {
            HealthPackSmall healthPackSmall = new HealthPackSmall(null);
        });
        assertThrows(NullPointerException.class, () -> {
            Shield shield = new Shield(null);
        });
    }

    @Test
    void playerPicksUpAmmo_returnTrue(){
        Inventory expectedInv = new Inventory(20, 50);
        Ammo ammo = new Ammo(map.getCell(2,2));
        ammo.pickUp(player);

        int expected = expectedInv.getAmmo();
        int result = player.getInventory().getAmmo();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpShield_returnTrue(){
        int expected = 100;
        Shield shield = new Shield(map.getCell(2,2));
        shield.pickUp(player);

        int result = player.getMaxHealth();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpHealthPackBig_returnTrue(){
        player.setHealth(0);
        int expected = 50;
        HealthPackBig healthPackBig = new HealthPackBig(map.getCell(2,2));
        healthPackBig.pickUp(player);

        int result = player.getHealth();

        assertEquals(expected, result);
    }

    @Test
    void playerPicksUpHealthPackSmall_returnTrue(){
        player.setHealth(40);
        int expected = 50;
        HealthPackSmall healthPackSmall = new HealthPackSmall(map.getCell(2,2));
        healthPackSmall.pickUp(player);

        int result = player.getHealth();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfAmmo_returnTrue () {
        String expected = "ammo";

        Ammo ammo = new Ammo(map.getCell(0,0));
        String result = ammo.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfBigHP_returnTrue () {
        String expected = "medkit50";

        HealthPackBig healthPackBig = new HealthPackBig(map.getCell(0,0));
        String result = healthPackBig.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfSmallHP_returnTrue () {
        String expected = "medkit10";

        HealthPackSmall healthPackSmall = new HealthPackSmall(map.getCell(0,0));
        String result = healthPackSmall.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfShield_returnTrue () {
        String expected = "shield";

        Shield shield = new Shield(map.getCell(0,0));
        String result = shield.getTileName();

        assertEquals(expected, result);
    }
}