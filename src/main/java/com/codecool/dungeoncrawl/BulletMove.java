package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.LinkedList;

public class BulletMove implements Runnable{
    Player player;
    Main main;

    public BulletMove(Player player, Main main) {
        this.player = player;
        this.main = main;
    }

    @Override
    public void run() {
        while (true) {
            if (player.getInventory().getActiveGun() != null) {
                LinkedList<Bullet> bullets = player.getInventory().getActiveGun().getBullets();
                bullets.removeIf(bullet -> !bullet.isAlive());
                for (Bullet bullet : bullets) {
                    bullet.move(bullet.getDirection().getX(), bullet.getDirection().getY());
                }
                main.refresh();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
