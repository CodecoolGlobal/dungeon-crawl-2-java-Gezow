package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Runner;
import com.codecool.dungeoncrawl.logic.actors.Walker;
import org.junit.jupiter.api.Test;
import org.tritonus.share.TSettings;

import java.util.Arrays;
import java.util.Objects;

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
    void enrageMovesActorCloserToTarget() {
        int[] goodMove1 = new int[]{1, 2};
        int[] goodMove2 = new int[]{2, 1};
        int[] goodMove3 = new int[]{2, 2};

        Runner runner = new Runner(gameMap.getCell(1, 1));
        Player player = new Player(gameMap.getCell(3, 3));

        runner.enrage(player.getX(),player.getY(),runner.getX(),runner.getY());
        int[] runnerCoordinate = new int[] {runner.getX(),runner.getY()};

        assertTrue(Arrays.equals(runnerCoordinate, goodMove1) || Arrays.equals(runnerCoordinate, goodMove2) || Arrays.equals(runnerCoordinate, goodMove3));
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
    void

}