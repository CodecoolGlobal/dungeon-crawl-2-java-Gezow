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
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
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


    public void monstersMove(){
        LinkedList<Actor> monsters = MapLoader.getMonsters();
        Player player = map.getPlayer();
        MyRunnable monsterMove = new MyRunnable(monsters, player, this);
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
    public void start(Stage primaryStage) throws Exception {
        musicPlayer();
        monstersMove();
        GridPane ui = new GridPane();
        ui.setPrefWidth(180);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Ammo: "), 0, 1);
        ui.add(ammoLabel, 1, 1);
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
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(Direction.NORTH.getX(), Direction.NORTH.getY());
                refresh();
                refreshFX();
                break;
            case DOWN:
                map.getPlayer().move(Direction.SOUTH.getX(), Direction.SOUTH.getY());
                refresh();
                refreshFX();
                break;
            case LEFT:
                map.getPlayer().move(Direction.WEST.getX(), Direction.WEST.getY());
                refresh();
                refreshFX();
                break;
            case RIGHT:
                map.getPlayer().move(Direction.EAST.getX(), Direction.EAST.getY());
                refresh();
                refreshFX();
                break;
            case E:
                if(map.getPlayer().getCell().getItem() != null){
                    map.getPlayer().getCell().getItem().pickUp(map.getPlayer());
                    refresh();
                    refreshFX();
                }
                break;
            case W:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.NORTH);
                refresh();
                refreshFX();
                break;
            case S:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.SOUTH);
                refresh();
                refreshFX();
                break;
            case A:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.WEST);
                refresh();
                refreshFX();
                break;
            case D:
                soundEffect(map.getPlayer().getInventory().getActiveGun());
                map.getPlayer().shoot(Direction.EAST);
                refresh();
                refreshFX();
                break;
        }
    }
    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }
                else if (cell.getBullet() != null){
                    Tiles.drawTile(context, cell.getBullet(), x, y);
                }
                else if (cell.getItem() != null){
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }
                else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
    }

    public void refreshFX(){
        healthLabel.setText("" + map.getPlayer().getHealth());
        ammoLabel.setText(map.getPlayer().getInventory().getAmmo() + "/" + map.getPlayer().getInventory().getMaxAmmo());
        for(int i = 0; i < map.getPlayer().getInventory().getGuns().size(); i++){
            Gun gun = map.getPlayer().getInventory().getGuns().get(map.getPlayer().getInventory().getGuns().keySet().toArray()[i]);
            Tiles.drawTile(gunContext, gun, i, 0);
        }
        for(int i = 0; i < map.getPlayer().getInventory().getCollectibles().size(); i++){
            Collectible item = map.getPlayer().getInventory().getCollectibles().get(i);
            Tiles.drawTile(itemContext, item, i, 0);
        }
    }
}
