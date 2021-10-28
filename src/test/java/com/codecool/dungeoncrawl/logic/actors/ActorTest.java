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

    @Test
    void getTileNameOfWalker_returnTrue () {
        String expected = "walker";

        Walker walker = new Walker(gameMap.getCell(1,2));
        String result = walker.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfRunner_returnTrue () {
        String expected = "runner";

        Runner runner = new Runner(gameMap.getCell(1,1));
        String result = runner.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfBulky_returnTrue () {
        String expected = "bulky";

        Bulky bulky = new Bulky(gameMap.getCell(0,0));
        String result = bulky.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void getTileNameOfActor_returnTrue () {
        String expected = "player";

        Player player = new Player(gameMap.getCell(0,0));
        String result = player.getTileName();

        assertEquals(expected, result);
    }

    @Test
    void createActorWithoutCellProvided_trhowsException(){
        assertThrows(NullPointerException.class, () -> {
            Player player = new Player(null);
        });
        assertThrows(NullPointerException.class, () -> {
            Bulky bulky = new Bulky(null);
        });
        assertThrows(NullPointerException.class, () -> {
            Runner runner = new Runner(null);
        });
        assertThrows(NullPointerException.class, () -> {
            Walker walker = new Walker(null);
        });
    }

    @Test
    void monsterWithoutPlayerDoesNotMove_throwsException () {
        Bulky bulky = new Bulky(gameMap.getCell(0,0));
        Runner runner = new Runner(gameMap.getCell(1,1));
        Walker walker = new Walker(gameMap.getCell(2,2));
        assertThrows(NullPointerException.class, () -> {
            bulky.autoMove(5, null);
        });
        assertThrows(NullPointerException.class, () -> {
            runner.autoMove(6,null);
        });
    }

    @Test
    void getPlayerMaxHealthAfterCreated_returnTrue () {
        int expected = 50;

        Player player = new Player(gameMap.getCell(0,0));
        int result = player.getMaxHealth();

        assertEquals(expected, result);
    }

    @Test
    void getPlayerHealthAfterNegativEffect_returnTrue () {
        int expected = 30;

        Player player = new Player(gameMap.getCell(0,0));
        player.setHealth(30);
        int result = player.getHealth();

        assertEquals(expected, result);

    }
}