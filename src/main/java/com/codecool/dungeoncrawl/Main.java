package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.collectibles.Collectible;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.LinkedList;

public class Main extends Application {
    String currentMap = "/map.txt";
    GameMap map = MapLoader.loadMap(currentMap);
    Canvas canvas = new Canvas(
            9 * Tiles.TILE_WIDTH,
            9 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label ammoLabel = new Label();
    Canvas gunCanvas = new Canvas(
            4 * Tiles.TILE_WIDTH,
            Tiles.TILE_WIDTH);
    GraphicsContext gunContext = gunCanvas.getGraphicsContext2D();
    Canvas itemCanvas = new Canvas(
            4 * Tiles.TILE_WIDTH,
            4 * Tiles.TILE_WIDTH);
    GraphicsContext itemContext = itemCanvas.getGraphicsContext2D();
    AudioFilePlayer audioFilePlayer = new AudioFilePlayer();

    public static void main(String[] args) {
        launch(args);
    }


    public void monstersMove(LinkedList<Actor> monsters){
        Player player = map.getPlayer();
        AutomaticMovement monsterMove = new AutomaticMovement(monsters, player, this);
        Thread thread = new Thread(monsterMove);
        thread.start();

    }

    public void soundEffect(Gun gun){
        SoundEffects soundEffects = new SoundEffects(gun);
        Thread thread2 = new Thread(soundEffects);
        thread2.start();
    }

    public void musicPlayer(){
        MusicPlayer musicPlayer = new MusicPlayer(audioFilePlayer);
        Thread thread1 = new Thread(musicPlayer);
        thread1.start();
    }


    @Override
    public void start(Stage primaryStage) {
        musicPlayer();
        monstersMove(MapLoader.getMonsters());
        GridPane ui = new GridPane();
        ui.setPrefWidth(180);
        ui.setPadding(new Insets(10));
        ui.add(healthLabel, 0, 0);
        ui.add(ammoLabel, 0, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(new Label("Guns: "), 0, 3);
        ui.add(gunCanvas,0 , 4);
        ui.add(new Label("Artifacts: "), 0, 5);
        ui.add(itemCanvas, 0, 6);
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        refreshFX();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        int x;
        int y;
        switch (keyEvent.getCode()) {
            case UP:
                x = Direction.NORTH.getX();
                y = Direction.NORTH.getY();
                moveAction(x, y);
                break;
            case DOWN:
                x = Direction.SOUTH.getX();
                y = Direction.SOUTH.getY();
                moveAction(x, y);
                break;
            case LEFT:
                x = Direction.WEST.getX();
                y = Direction.WEST.getY();
                moveAction(x, y);
                break;
            case RIGHT:
                x = Direction.EAST.getX();
                y = Direction.EAST.getY();
                moveAction(x, y);
                break;
            case E:
                if(map.getPlayer().getCell().getItem() != null){
                    map.getPlayer().getCell().getItem().pickUp(map.getPlayer());
                                    }
                break;
            case W:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.NORTH);
                break;
            case S:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.SOUTH);
                break;
            case A:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.WEST);
                break;
            case D:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.EAST);
                break;
            case M:
                mapCheck();
                break;
            case Q:
                map.getPlayer().changeGun(-1);
                break;
            case R:
                map.getPlayer().changeGun(1);
                break;
        }
        refresh();
        refreshFX();
    }
    public void refresh() {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = playerX - 4; x < playerX + 5; x++) {
            for (int y = playerY - 4; y < playerY + 5; y++) {
                Cell cell;
                try {
                    cell = map.getCell(x, y);
                } catch (IndexOutOfBoundsException IOBcamera) {
                    cell = new Cell(CellType.EMPTY);
                }
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x - playerX + 4, y - playerY + 3);
                } else if (cell.getBullet() != null) {
                    Tiles.drawTile(context, cell.getBullet(), x - playerX + 4, y - playerY + 3);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x - playerX + 4, y - playerY + 3);
                } else {
                    Tiles.drawTile(context, cell, x - playerX + 4, y - playerY + 3);
                }
            }
        }
    }

    public void refreshFX(){
        healthLabel.setText("Health: " + map.getPlayer().getHealth() + "/" + map.getPlayer().getMaxHealth());
        ammoLabel.setText("Ammo: " + map.getPlayer().getInventory().getAmmo() + "/" + map.getPlayer().getInventory().getMaxAmmo());
        for(int i = 0; i < map.getPlayer().getInventory().getGuns().size(); i++){
            Gun gun = map.getPlayer().getInventory().getGuns().get(map.getPlayer().getInventory().getGuns().keySet().toArray()[i]);
            Tiles.drawTile(gunContext, gun, i, 0);
        }
        for(int i = 0; i < map.getPlayer().getInventory().getCollectibles().size(); i++){
            Collectible item = map.getPlayer().getInventory().getCollectibles().get(i);
            Tiles.drawTile(itemContext, item, i, 0);
        }
    }

    public void moveAction (int x, int y){
        map.getPlayer().move(x, y);
        Cell nextCell = map.getPlayer().getCell().getNeighbor(x, y);
        Inventory inventory = map.getPlayer().getInventory();
        if (nextCell.getTileName().equals("door") && map.getPlayer().getInventory().getCollectibles().containsKey("key")){
            if(currentMap.equals("/map.txt")){
                currentMap="/map2.txt";
                map = MapLoader.loadMap(currentMap);
                map.getPlayer().setInventory(inventory);
                monstersMove(MapLoader.getMonsters());
            }
        }
        else if(nextCell.getTileName().equals("door") && map.getPlayer().getInventory().getCollectibles().containsKey("crystal")){
            if(currentMap.equals("/map2.txt")){
                currentMap="/map3.txt";
                map = MapLoader.loadMap(currentMap);
                map.getPlayer().setInventory(inventory);
                monstersMove(MapLoader.getMonsters());
            }
        }
        else if(nextCell.getTileName().equals("portal")){
            if (currentMap.equals("/map.txt")){
                currentMap="/mapTrap.txt";
                map = MapLoader.loadMap(currentMap);
                map.getPlayer().setInventory(inventory);
                monstersMove(MapLoader.getMonsters());
            }
            else if (currentMap.equals("/mapTrap.txt")){
                currentMap="/map2.txt";
                map = MapLoader.loadMap(currentMap);
                map.getPlayer().setInventory(inventory);
                monstersMove(MapLoader.getMonsters());
            }
        }
        refresh();
        refreshFX();
    }

    private void mapCheck() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
