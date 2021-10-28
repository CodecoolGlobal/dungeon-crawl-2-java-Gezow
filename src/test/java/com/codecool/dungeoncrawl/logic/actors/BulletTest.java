package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    GameMap gameMap = new GameMap(4, 4, CellType.FLOOR);

    @Test
    void bulletHitAnActorDoDamageAndDeleted() {
        Bullet bullet = new Bullet(gameMap.getCell(2,2), Direction.NORTH,1);
        Walker walker = new Walker(gameMap.getCell(2,1));
        int maxHealth = walker.getHealth();
        Direction direction = bullet.getDirection();
        bullet.move(direction.getX(),direction.getY());

        assertNull(gameMap.getCell(2, 2).getBullet());
        assertNull(gameMap.getCell(2, 1).getBullet());
        assertTrue(maxHealth != gameMap.getCell(2, 1).getActor().getHealth());
    }

    @Test
    void nameIsBullet(){
        Bullet bullet = new Bullet(gameMap.getCell(2,2), Direction.NORTH,1);

        assertEquals("bullet", bullet.getTileName());
    }
}
