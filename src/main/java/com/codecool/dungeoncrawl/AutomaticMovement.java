package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.LinkedList;

public class AutomaticMovement implements Runnable {
    private LinkedList<Actor> monsters;
    private final Player player;
    private final Main main;
    private final AudioFilePlayer audioFilePlayer;
    private boolean isRunning;

    public AutomaticMovement(LinkedList<Actor> monsters, Player player, Main main, AudioFilePlayer audioFilePlayer) {
        this.monsters = monsters;
        this.player = player;
        this.main = main;
        this.audioFilePlayer = audioFilePlayer;
        this.isRunning = true;
    }

    @Override
    public void run() {
        int frag = 1;
        while (monsters.size() > 0) {
            if (isRunning) {
                monsters.removeIf(monster -> !monster.isAlive());
                for (Actor monster : monsters) {
                    if (monster.canAttackPlayer()) {
                        monster.attack(player);
                    } else {
                        monster.autoMove(frag, player);
                    }
                }
                main.refresh();
                frag++;
            }
                try {
                    Thread.sleep(210 - (Settings.GAME_SPEED.getValue() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
        if (monsters.size() <= 0) {
            audioFilePlayer.play("src/main/resources/monsterkill.wav");
        }


    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
