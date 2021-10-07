package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import com.codecool.dungeoncrawl.logic.items.guns.Pistol;

import java.util.HashMap;

public class Inventory {
    private int ammo;
    private int maxAmmo;
    private Gun activeGun;
    private final HashMap<String, Gun> guns = new HashMap<>();
    private final HashMap<String, Collectible> collectibles = new HashMap<>();

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

    public Gun getActiveGun(){
        return activeGun;
    }

    public void setActiveGun(Gun gun){
        if(activeGun != null) activeGun.setActive(false);
        activeGun = gun;
        activeGun.setActive(true);
    }
}
