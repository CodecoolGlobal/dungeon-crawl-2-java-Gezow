# Dungeon Crawl/DOOM
![doomgame](https://user-images.githubusercontent.com/79324489/167413192-e4a07c15-6afd-4ac3-aed3-9d61a9309c14.png)

## Introduction

With this teamwork project we were about two thirds of the way through the OOP module. We have mostly mastered Java, and the OOP and SOLID principles.

For the first sprint we were given skeleton code for a game, which included the UI, player movement, and a very basic map. Our task was to make up a theme for it and extend it by implementing enemies, items, new maps and so on.

We quickly decided our game would be a rouglike version of DOOM and had a few extra ideas, such as: music, enemies (and bullets) move independently of the player, and a wide variety of items, weapons, and enemies.

On the second sprint we had less creative tasks, like test coverage, game saving and bugfixing.

## Features

- Iconic DOOM music (multithread)
- Sound effects (multithread)[VOLUME WARNING!]
- Independent enemy movement (multithread)
- Different enemy movement patterns
- Multiple weapons with different shooting patterns

## Prerequisites

- Java 8 JDK
- Java Runtime Environment

## Install

``` sh
git clone https://github.com/CodecoolGlobal/dungeon-crawl-2-java-Gezow
cd dungeon-crawl-2-java-Gezow/
mvn compile
mvn javafx:run
```

##Controlls

Movement - Arrow keys
Shooting - WASD
Picking up items - E
Switching weapons - Q or R
