package com.codecool.dungeoncrawl.logic;

public enum Settings {
    WINDOW(7), // 7
    LINE_OF_SIGHT(4), // 4
    CANVAS_WIDTH(9), // 9
    CANVAS_HEIGHT(9), // 9

    GAME_SPEED(10), // 8 (10-20)

    PLAYER_MAX_HEALTH(50), // 50
    PLAYER_MELEE_DAMAGE(5), // 5
    PLAYER_MAX_AMMO(50), // 50
    PLAYER_STARTING_AMMO(10), //10

    WALKER_HEALTH(10), // 10
    WALKER_DAMAGE(1), // 1
    WALKER_SPEED(5), // 5 (1-10)

    RUNNER_HEALTH(5), // 5
    RUNNER_DAMAGE(1), // 1
    RUNNER_SPEED(5), // 5 (1-10)
    RUNNER_RAGE_SPEED(8), // 8 (1-10)
    RUNNER_FOLLOW_DISTANCE(5), // 5

    BULKY_HEALTH(30), // 30
    BULKY_DAMAGE(2), // 2
    BULKY_SPEED(1), // 1 (1-10)
    BULKY_RAGE_SPEED(6),// 6 (1-10)
    BULKY_FOLLOW_DISTANCE(10), // 10
    BULKY_RAGE_HEALTH(20), // 20

    PISTOL_DAMAGE(3), // 3
    SHOTGUN_DAMAGE(10), // 10
    A_RIFLE_DAMAGE(8), // 8
    BFG_DAMAGE(20), // 20

    AMMO_PACK(5), // 5
    HEALTH_PACK_SMALL(10), // 10
    HEALTH_PACK_BIG(50), // 50
    SHIELD(50), // 50
    ;

    Settings(int value) {
        this.value = value;
    }
    private final int value;

    public int getValue() {
        return value;
    }
}
