package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(4, 4, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void canMoveReturnsFalseIfOutOfFields() {
        GameMap gameMap = new GameMap(3, 3, CellType.EMPTY);
        Player player = new Player(gameMap.getCell(1, 1));
        boolean playerCanMve = player.canMove(1, 0);

        assertFalse(playerCanMve);
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1));
        Walker walker = new Walker(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, walker.getX());
        assertEquals(1, walker.getY());
        assertEquals(walker, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void enrageMovesActorCloserToTarget1() {
        int[] goodMove = new int[]{1, 2};
        Runner runner = new Runner(gameMap.getCell(1, 1));
        Player player = new Player(gameMap.getCell(1, 3));

        runner.enrage(player.getX(),player.getY(),runner.getX(),runner.getY());
        int[] runnerCoordinate = new int[] {runner.getX(),runner.getY()};

        assertArrayEquals(runnerCoordinate, goodMove);
    }

    @Test
    void enrageMovesActorCloserToTarget2() {
        int[] goodMove = new int[]{1, 1};
        Runner runner = new Runner(gameMap.getCell(2, 2));
        Player player = new Player(gameMap.getCell(0, 0));

        runner.enrage(player.getX(),player.getY(),runner.getX(),runner.getY());
        int[] runnerCoordinate = new int[] {runner.getX(),runner.getY()};

        assertArrayEquals(runnerCoordinate, goodMove);
    }

    @Test
    void canAttackPlayerReturnsTrueIfNextToPlayer(){
        Runner runner = new Runner(gameMap.getCell(1, 1));
        Player player = new Player(gameMap.getCell(2, 1));

        assertTrue(runner.canAttackPlayer());
    }

    @Test
    void attackLowerTheTargetsHealth(){
        Runner runner = new Runner(gameMap.getCell(1, 1));
        Player player = new Player(gameMap.getCell(2, 1));
        int loweredHealth = player.getMaxHealth() - Settings.RUNNER_DAMAGE.getValue();

        runner.attack(player);

        assertEquals(player.getHealth(),loweredHealth);
    }
}