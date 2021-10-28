package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShootTest {
    Player player;
    GameMap expectedMap;
    GameMap map;
    Pistol pistol;
    Shotgun shotgun;
    AutomaticRifle automaticRifle;
    Bfg bfg;
    Inventory expectedInventory;

    @BeforeEach
    void setUp(){
        map = new GameMap(3,3, CellType.FLOOR);
        expectedMap = new GameMap(3,3, CellType.FLOOR);
        player = new Player(map.getCell(1,1));
        map.setPlayer(player);
        expectedMap.setPlayer(player);
        pistol = new Pistol(map.getCell(1,1));
        shotgun = new Shotgun(map.getCell(1,1));
        automaticRifle = new AutomaticRifle(map.getCell(1,1));
        bfg = new Bfg(map.getCell(1,1));
        expectedInventory = new Inventory(Settings.PLAYER_STARTING_AMMO.getValue(), Settings.PLAYER_MAX_AMMO.getValue());
    }

    @Test
    void every_gun_removes_one_bullet_returns_true() {
        pistol.pickUp(player);
        shotgun.pickUp(player);
        automaticRifle.pickUp(player);
        bfg.pickUp(player);
        for(Gun gun: player.getInventory().getGuns().values()){
            expectedInventory.setAmmo(expectedInventory.getAmmo() - 1);

            player.shoot(Direction.EAST);
            player.changeGun(1);

            assertEquals(expectedInventory.getAmmo(), player.getInventory().getAmmo());
        }
    }

    @Test
    void shoot_pistol_returns_true() {
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.EAST);
        placeExpectedBullets(directions, Settings.PISTOL_DAMAGE.getValue());

        pistol.pickUp(map.getPlayer());
        shootGun(Direction.EAST);

        Bullet expected = expectedMap.getPlayer().getCell().getNeighbor(Direction.EAST.getX(), Direction.EAST.getY()).getBullet();
        Bullet result = map.getPlayer().getCell().getNeighbor(Direction.EAST.getX(), Direction.EAST.getY()).getBullet();
        assertEquals(expected, result);
    }

    @Test
    void shoot_shotgun_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.SOUTHWEST);
        placeExpectedBullets(directions, Settings.SHOTGUN_DAMAGE.getValue());

        shotgun.pickUp(player);
        shootGun(Direction.SOUTH);

        for(Direction direction:directions){
            Bullet expected = expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            Bullet result = map.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            assertEquals(expected, result);
        }
    }

    @Test
    void shoot_automatic_rifle_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.SOUTHWEST);
        directions.add(Direction.WEST);
        directions.add(Direction.NORTHWEST);
        placeExpectedBullets(directions, Settings.A_RIFLE_DAMAGE.getValue());

        automaticRifle.pickUp(player);
        shootGun(Direction.WEST);

        for(Direction direction:directions){
            Bullet expected = expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            Bullet result = map.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            assertEquals(expected, result);
        }
    }

    @Test
    void shoot_bfg_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.NORTH);
        directions.add(Direction.NORTHEAST);
        placeExpectedBullets(directions, Settings.BFG_DAMAGE.getValue());

        bfg.pickUp(player);
        shootGun(Direction.NORTH);

        for(Direction direction:directions){
            Bullet expected = expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            Bullet result = map.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).getBullet();
            assertEquals(expected, result);
        }
    }

    void shootGun(Direction direction){
        map.getPlayer().shoot(direction);
        for(Bullet bullet: player.getInventory().getActiveGun().getBullets()){
            bullet.move(bullet.getDirection().getX(), bullet.getDirection().getY());
        }
    }

    void placeExpectedBullets(List<Direction> directions, int damage){
        for(Direction direction:directions){
            Bullet bullet = new Bullet(
                    expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()),
                    direction,
                    damage
            );
            expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()).setBullet(bullet);
        }
    }
}