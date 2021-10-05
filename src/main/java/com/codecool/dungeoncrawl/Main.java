package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
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
            9 * Tiles.TILE_WIDTH,
            9 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label ammoLabel = new Label();

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

    @Override
    public void start(Stage primaryStage) {
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
        ui.add(new Label("Artifacts: "), 0, 5);
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        refreshFX();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                refreshFX();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                refreshFX();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                refreshFX();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                refreshFX();
                break;
        }
    }

    public void refresh() {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = playerX-4; x < playerX+5; x++) {
            for (int y = playerY-4; y < playerY+5; y++) {
                Cell cell;
                try {
                    cell = map.getCell(x, y);
                }catch (IndexOutOfBoundsException IOBcamera){
                    cell = new Cell(CellType.EMPTY);
                }
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x-playerX+4, y-playerY+3);
                } else {
                    Tiles.drawTile(context, cell, x-playerX+4, y-playerY+3);
                }
            }
        }
    }

    public void refreshFX(){
        healthLabel.setText("" + map.getPlayer().getHealth());
        ammoLabel.setText(map.getPlayer().getAmmo() + "/" + map.getPlayer().getMaxAmmo());
    }
}
