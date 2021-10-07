package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Direction;

public class Shotgun extends Gun{
    public Shotgun(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isActive) return "shotgun-active";
        else return "shotgun";
    }

    @Override
    public void shoot(Cell cell, Direction direction){
        super.shoot(cell, direction, 10);
        switch (direction){
            case NORTH:
                super.shoot(cell, Direction.NORTHEAST, 10);
                super.shoot(cell, Direction.NORTHWEST, 10);
                break;
            case SOUTH:
                super.shoot(cell, Direction.SOUTHEAST, 10);
                super.shoot(cell, Direction.SOUTHWEST, 10);
                break;
            case EAST:
                super.shoot(cell, Direction.NORTHEAST, 10);
                super.shoot(cell, Direction.SOUTHEAST, 10);
                break;
            case WEST:
                super.shoot(cell, Direction.SOUTHWEST, 10);
                super.shoot(cell, Direction.NORTHWEST, 10);
                break;
        }
    }
}
