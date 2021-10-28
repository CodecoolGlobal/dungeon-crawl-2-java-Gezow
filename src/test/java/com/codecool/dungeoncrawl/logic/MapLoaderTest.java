package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapLoaderTest {

    GameMap gameMap = MapLoader.loadMap("/mapTest.txt");

    @Test
    void mapLoaderMakeCorrectTiles(){
        assertEquals(CellType.EMPTY, gameMap.getCell(0,1).getType());
        assertEquals(CellType.FLOOR, gameMap.getCell(0,0).getType());
        assertEquals("walker", gameMap.getCell(1, 0).getActor().getTileName());
        assertEquals("runner", gameMap.getCell(2, 0).getActor().getTileName());
    }

    @Test
    void mapHasAllMonstersInMonstersList(){
        LinkedList<Actor> monsters = new LinkedList<>();
        monsters.add(gameMap.getCell(1, 0).getActor());
        monsters.add(gameMap.getCell(2, 0).getActor());
        monsters.add(gameMap.getCell(3, 0).getActor());

        LinkedList<Actor> testedMonsters = MapLoader.getMonsters();

        assertEquals(testedMonsters,monsters);
    }

    @Test
    void mapLoaderThrowsRuntimeExceptionOnUnrecognisedChar(){
        assertThrows(RuntimeException.class, ()-> MapLoader.loadMap("/mapTestWrongChar.txt"));
    }
}
