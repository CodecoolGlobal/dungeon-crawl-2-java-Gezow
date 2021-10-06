package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.collectibles.*;
import com.codecool.dungeoncrawl.logic.items.consumables.*;
import com.codecool.dungeoncrawl.logic.items.guns.*;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MapLoader {
    private static LinkedList<Actor> monsters;
    public static GameMap loadMap() {
        monsters = new LinkedList<>();
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            Walker walker = new Walker(cell);
                            monsters.add(walker);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            Runner runner = new Runner(cell);
                            monsters.add(runner);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            Bulky bulky = new Bulky(cell);
                            monsters.add(bulky);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            Medkit10 medkit10 = new Medkit10(cell);
                            break;
                        case 'M':
                            cell.setType(CellType.FLOOR);
                            Medkit50 medkit50 = new Medkit50(cell);
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            Ammo ammo = new Ammo(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            Pistol pistol = new Pistol(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Shotgun shotgun = new Shotgun(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            AutomaticRifle automaticRifle = new AutomaticRifle(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            Bfg bfg = new Bfg(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            Key key = new Key(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            Crystal crystal = new Crystal(cell);
                            break;
                        case 'R':
                            cell.setType(CellType.FLOOR);
                            Rocket rocket = new Rocket(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static LinkedList<Actor> getMonsters() {
        return monsters;
    }
}
