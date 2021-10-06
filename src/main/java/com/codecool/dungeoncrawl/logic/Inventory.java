package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;

import java.util.HashMap;

public class Inventory {
    private int ammo;
    private int maxAmmo;
    private HashMap<String, Gun> guns = new HashMap<>();
    private HashMap<String, Collectible> collectibles = new HashMap<>();

    public Inventory(int ammo, int maxAmmo){
        this.ammo = ammo;
        this.maxAmmo = maxAmmo;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public HashMap<String, Gun> getGuns() {
        return guns;
    }

    public HashMap<String, Collectible> getCollectibles() {
        return collectibles;
    }
}
