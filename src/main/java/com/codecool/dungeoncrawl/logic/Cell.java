package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Item item;
    private Actor actor;
    private Bullet bullet;
    private GameMap gameMap;
    private int x;
    private int y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell(CellType type){
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
        if (actor != null){
            actor.setCell(this);
        }
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        if(item != null){
            item.setCell(this);
        }
    }
    public boolean hasActor(){
        return actor != null;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}
