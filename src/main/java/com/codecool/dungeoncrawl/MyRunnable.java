package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.LinkedList;

public class MyRunnable implements Runnable{
    private LinkedList<Actor> monsters;
    private Player player;
    private Main main;

    public MyRunnable(LinkedList<Actor> monsters, Player player, Main main) {
        this.monsters = monsters;
        this.player = player;
        this.main = main;
    }

    @Override
    public void run() {
        while (true) {
            for (Actor monster : monsters) {
                if(monster.canAttackPlayer()){
                    monster.attack(player);
                }else {
                    monster.move(Direction.getRandom().getX(), Direction.getRandom().getY());
                }
            }
            main.refresh();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
