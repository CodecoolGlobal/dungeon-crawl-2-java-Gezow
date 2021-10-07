package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Settings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = Settings.WINDOW.getValue() * 16;

    private static final Image tileset = new Image("/tiles.png", 543 * Settings.WINDOW.getValue(), 543 * Settings.WINDOW.getValue(), true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + Settings.WINDOW.getValue());
            y = j * (TILE_WIDTH + Settings.WINDOW.getValue());
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 3));
        tileMap.put("walker", new Tile(28, 6));
        tileMap.put("runner", new Tile(27, 6));
        tileMap.put("bulky", new Tile(30, 6));
        tileMap.put("medkit10", new Tile(12, 26));
        tileMap.put("medkit50", new Tile(11, 26));
        tileMap.put("ammo", new Tile(12, 31));
        tileMap.put("key", new Tile(18,23));
        tileMap.put("crystal", new Tile(18, 22));
        tileMap.put("rocket0", new Tile(15, 23));
        tileMap.put("rocket1", new Tile(15, 22));
        tileMap.put("rocket2", new Tile(15, 21));
        tileMap.put("rocket3", new Tile(15, 20));
        tileMap.put("pistol", new Tile(6, 31));
        tileMap.put("shotgun", new Tile(7, 31));
        tileMap.put("automaticrifle", new Tile(8, 31));
        tileMap.put("bfg", new Tile(11, 31));
        tileMap.put("bullet", new Tile(27, 21));
        tileMap.put("shield", new Tile(2, 23));
        tileMap.put("door", new Tile(4,9));
        tileMap.put("portal", new Tile(1,9));
        tileMap.put("flame", new Tile(14,10));
        tileMap.put("fire", new Tile(15,10));
        tileMap.put("hellcrystal", new Tile(6,2));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
