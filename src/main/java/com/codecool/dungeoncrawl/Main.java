package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.collectibles.*;
import com.codecool.dungeoncrawl.logic.items.collectibles.Crystal;
import com.codecool.dungeoncrawl.logic.items.collectibles.Key;
import com.codecool.dungeoncrawl.logic.items.guns.Gun;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Application {
    String currentMap = "/map.txt";
    GameMap map = MapLoader.loadMap(currentMap);
    Canvas canvas = new Canvas(
            Settings.CANVAS_WIDTH.getValue() * Tiles.TILE_WIDTH,
            Settings.CANVAS_HEIGHT.getValue() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label ammoLabel = new Label();
    Canvas gunCanvas = new Canvas(
            4 * Tiles.TILE_WIDTH,
            Tiles.TILE_WIDTH);
    GraphicsContext gunContext = gunCanvas.getGraphicsContext2D();
    Canvas itemCanvas = new Canvas(
            4 * Tiles.TILE_WIDTH,
            Tiles.TILE_WIDTH);
    GraphicsContext itemContext = itemCanvas.getGraphicsContext2D();
    Canvas rocketCanvas = new Canvas(
            4 * Tiles.TILE_WIDTH,
            Tiles.TILE_WIDTH);
    GraphicsContext rocketContext = rocketCanvas.getGraphicsContext2D();
    AudioFilePlayer audioFilePlayer = new AudioFilePlayer();
    AutomaticMovement monsterMove;
    PopUpWindow popUpWindow = new PopUpWindow();
    int gunCounter = 0;

    public static void main(String[] args) {
        launch(args);
    }


    public void monstersMove(LinkedList<Actor> monsters){
        Player player = map.getPlayer();
        monsterMove = new AutomaticMovement(monsters, player, this, audioFilePlayer);
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

    public void bulletMove(Player player){
        BulletMove bulletMove = new BulletMove(player,this);
        Thread thread3 = new Thread(bulletMove);
        thread3.start();
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
        ui.add(new Label("Rockets: "), 0, 7);
        ui.add(rocketCanvas, 0, 8);

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
        if (map.getPlayer().getInventory().getActiveGun() != null && gunCounter == 0){
            gunCounter ++;
            bulletMove(map.getPlayer());
        }
        if (map.getPlayer().getHealth() <= 0){
            map.getPlayer().setAlive(false);
            refreshFX();
            return;
        }
        switch (keyEvent.getCode()) {
            case UP:
                if (monsterMove.isRunning()){
                    movePlayer(Direction.NORTH);
                }
                break;
            case DOWN:
                if (monsterMove.isRunning()) {
                    movePlayer(Direction.SOUTH);
                }
                break;
            case LEFT:
                if (monsterMove.isRunning()) {
                    movePlayer(Direction.WEST);
                }
                break;
            case RIGHT:
                if (monsterMove.isRunning()) {
                    movePlayer(Direction.EAST);
                }
                break;
            case E:
                if(map.getPlayer().getCell().getItem() != null){
                    map.getPlayer().getCell().getItem().pickUp(map.getPlayer());
                                    }
                break;
            case W:
                playerShoot(Direction.NORTH);
                break;
            case S:
                playerShoot(Direction.SOUTH);
                break;
            case A:
                playerShoot(Direction.WEST);
                break;
            case D:
                playerShoot(Direction.EAST);
                break;
            case Q:
                map.getPlayer().changeGun(-1);
                break;
            case R:
                map.getPlayer().changeGun(1);
                break;
            case P:
                monsterMove.setRunning(false);
                break;
            case C:
                monsterMove.setRunning(true);
                break;
            case V:
                if (!monsterMove.isRunning()){
                    if (keyEvent.isControlDown()){

                        popUpWindow.display();
                    }
                }
                break;
        }
        refresh();
        refreshFX();
    }

    private void playerShoot(Direction direction) {
        int ammo = map.getPlayer().getInventory().getAmmo();
        if (ammo > 0) {
            soundEffect(map.getPlayer().getInventory().getActiveGun());
            map.getPlayer().shoot(direction);
            map.getPlayer().getInventory().setAmmo(ammo-1);
        }
    }

    private void movePlayer(Direction direction) {
        int x;
        int y;
        x = direction.getX();
        y = direction.getY();
        moveAction(x, y);
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
                    Tiles.drawTile(context, cell.getActor(), x - playerX + Settings.LINE_OF_SIGHT.getValue(), y - playerY + Settings.LINE_OF_SIGHT.getValue());
                } else if (cell.getBullet() != null) {
                    Tiles.drawTile(context, cell.getBullet(), x - playerX + Settings.LINE_OF_SIGHT.getValue(), y - playerY + Settings.LINE_OF_SIGHT.getValue());
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x - playerX + Settings.LINE_OF_SIGHT.getValue(), y - playerY + Settings.LINE_OF_SIGHT.getValue());
                } else {
                    Tiles.drawTile(context, cell, x - playerX + Settings.LINE_OF_SIGHT.getValue(), y - playerY + Settings.LINE_OF_SIGHT.getValue());
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
            if(!(item instanceof Rocket)) Tiles.drawTile(itemContext, item, i, 0);
        }
        for(int i = 0; i < map.getPlayer().getInventory().getRockets().size(); i++){
           Rocket rocket = map.getPlayer().getInventory().getRockets().get(i);
           Tiles.drawTile(rocketContext, rocket, i, 0);
        }
    }

    public void moveAction (int x, int y){
        map.getPlayer().move(x, y);
        ArrayList<Collectible> items = map.getPlayer().getInventory().getCollectibles();
        Cell nextCell = map.getPlayer().getCell().getNeighbor(x, y);
        Inventory inventory = map.getPlayer().getInventory();
        if(currentMap.equals("/map.txt")){
            if (nextCell.getTileName().equals("door") && hasKey(items)){
                setupNewMap("/map2.txt",inventory);
            }
            else if (nextCell.getTileName().equals("portal")){
                setupNewMap("/mapTrap.txt", inventory);
            }
        }
        else if(currentMap.equals("/map2.txt")){
            if(nextCell.getTileName().equals("door")){
                int counter = crystalCounter(items);
                if (counter == 3){
                    setupNewMap("/map3.txt", inventory);
                }
            }
        }
        else if(currentMap.equals("/mapTrap.txt")){
            if (nextCell.getTileName().equals("portal")) {
                setupNewMap("/map2.txt", inventory);
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

    private void setupNewMap(String newMap, Inventory inventory){
        currentMap=newMap;
        int health = map.getPlayer().getHealth();
        int maxHealth = map.getPlayer().getMaxHealth();
        map = MapLoader.loadMap(currentMap);
        map.getPlayer().setInventory(inventory);
        map.getPlayer().setMaxHealth(maxHealth);
        map.getPlayer().setHealth(health);
        monsterMove.setRunning(false);
        monstersMove(MapLoader.getMonsters());
    }

    private boolean hasKey(ArrayList items){
        for (Object item: items){
            if (item instanceof Key){
                return true;
            }
        }
        return false;
    }

    private int crystalCounter(ArrayList items){
        int count = 0;
        for (Object item: items){
            if (item instanceof Crystal){
                System.out.println(((Crystal) item).getTileName());
                count++;
                System.out.println(count);
            }
        }
        return count;
    }
}
