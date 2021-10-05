package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class MyRunnable implements Runnable{
    private LinkedList<Actor> monsters;
    private Main main;

    public MyRunnable(LinkedList<Actor> monsters, Main main) {
        this.monsters = monsters;
        this.main = main;
    }

    @Override
    public void run() {
        while (true) {
            for (Actor monster : monsters) {
                monster.move(Direction.getRandom().getX(), Direction.getRandom().getY());
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
