package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        for(Gun gun: player.getInventory().getGuns()){
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
        List<Bullet> bullets = placeExpectedBullets(directions, Settings.PISTOL_DAMAGE.getValue());

        pistol.pickUp(map.getPlayer());
        shootGun(Direction.EAST);

        Cell expectedCell = bullets.get(0).getCell();
        Cell resultCell = map.getPlayer().getCell().getNeighbor(directions.get(0).getX(), directions.get(0).getY()).getBullet().getCell();
        Direction expectedDirection = bullets.get(0).getDirection();
        Direction resultDirection = map.getPlayer().getCell().getNeighbor(directions.get(0).getX(), directions.get(0).getY()).getBullet().getDirection();
        int expectedDamage = bullets.get(0).getDamage();
        int resultDamage = map.getPlayer().getCell().getNeighbor(directions.get(0).getX(), directions.get(0).getY()).getBullet().getDamage();
        assertEquals(expectedCell, resultCell);
        assertEquals(expectedDirection, resultDirection);
        assertEquals(expectedDamage, resultDamage);
    }

    @Test
    void shoot_shotgun_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.SOUTHWEST);
        List<Bullet> bullets = placeExpectedBullets(directions, Settings.SHOTGUN_DAMAGE.getValue());

        shotgun.pickUp(player);
        shootGun(Direction.SOUTH);

        for(int i = 0; i < directions.size(); i++){
            Cell expectedCell = bullets.get(i).getCell();
            Cell resultCell = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getCell();
            Direction expectedDirection = bullets.get(i).getDirection();
            Direction resultDirection = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDirection();
            int expectedDamage = bullets.get(i).getDamage();
            int resultDamage = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDamage();
            assertEquals(expectedCell, resultCell);
            assertEquals(expectedDirection, resultDirection);
            assertEquals(expectedDamage, resultDamage);
        }
    }

    @Test
    void shoot_automatic_rifle_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.WEST);
        directions.add(Direction.WEST);
        directions.add(Direction.WEST);
        List<Bullet> bullets = placeExpectedBullets(directions, Settings.A_RIFLE_DAMAGE.getValue());

        automaticRifle.pickUp(player);
        shootGun(Direction.WEST);

        for(int i = 0; i < directions.size(); i++){
            Cell expectedCell = bullets.get(i).getCell();
            Cell resultCell = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getCell();
            Direction expectedDirection = bullets.get(i).getDirection();
            Direction resultDirection = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDirection();
            int expectedDamage = bullets.get(i).getDamage();
            int resultDamage = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDamage();
            assertEquals(expectedCell, resultCell);
            assertEquals(expectedDirection, resultDirection);
            assertEquals(expectedDamage, resultDamage);
        }
    }

    @Test
    void shoot_bfg_returns_true(){
        List<Direction> directions = new LinkedList<>();
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.NORTH);
        directions.add(Direction.NORTHEAST);
        List<Bullet> bullets = placeExpectedBullets(directions, Settings.BFG_DAMAGE.getValue());

        bfg.pickUp(player);
        shootGun(Direction.NORTH);

        for(int i = 0; i < directions.size(); i++){
            Cell expectedCell = bullets.get(i).getCell();
            Cell resultCell = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getCell();
            Direction expectedDirection = bullets.get(i).getDirection();
            Direction resultDirection = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDirection();
            int expectedDamage = bullets.get(i).getDamage();
            int resultDamage = map.getPlayer().getCell().getNeighbor(directions.get(i).getX(), directions.get(i).getY()).getBullet().getDamage();
            assertEquals(expectedCell, resultCell);
            assertEquals(expectedDirection, resultDirection);
            assertEquals(expectedDamage, resultDamage);
        }
    }

    @Test
    void shooting_without_ammo_returns_false(){
        pistol.pickUp(map.getPlayer());
        map.getPlayer().getInventory().setAmmo(0);
        shootGun(Direction.EAST);
        
        assertThrows(NullPointerException.class, () -> {
            map.getPlayer().getCell().getNeighbor(Direction.EAST.getX(), Direction.EAST.getY()).getBullet().getCell();
        });
    }

    @Test
    void switching_weapons_up_goes_around_returns_true(){
        pistol.pickUp(player);
        shotgun.pickUp(player);
        automaticRifle.pickUp(player);
        bfg.pickUp(player);

        for(int i=0; i < player.getInventory().getGuns().size(); i++){
            player.changeGun(1);
        }

        assertEquals("bfg-active", player.getInventory().getActiveGun().getTileName());
    }

    @Test
    void switching_weapons_down_goes_around_returns_true(){
        automaticRifle.pickUp(player);
        shotgun.pickUp(player);
        pistol.pickUp(player);
        bfg.pickUp(player);

        for(int i=0; i < player.getInventory().getGuns().size(); i++){
            player.changeGun(-1);
            Gun activeGun = player.getInventory().getActiveGun();
        }

        assertEquals("bfg-active", player.getInventory().getActiveGun().getTileName());
    }

    void shootGun(Direction direction){
        map.getPlayer().shoot(direction);
        for(Bullet bullet: player.getInventory().getActiveGun().getBullets()){
            bullet.move(bullet.getDirection().getX(), bullet.getDirection().getY());
        }
    }

    List<Bullet> placeExpectedBullets(List<Direction> directions, int damage){
        ArrayList<Bullet> bullets = new ArrayList<>();
        for(Direction direction:directions){
            Bullet bullet = new Bullet(
                    expectedMap.getPlayer().getCell().getNeighbor(direction.getX(), direction.getY()),
                    direction,
                    damage
            );
            bullets.add(bullet);
        }
        return bullets;
    }
}