package com.codecool.dungeoncrawl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;


public class PopUpWindow {


    public void display(JsonObject map)
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Save menu");


        Label label1= new Label("Do you want to save your game state?");

        TextField textField = new TextField();

        Button button1= new Button("Cancel");

        Button button2 = new Button("Save");


        button1.setOnAction(e -> popupwindow.close());
        button2.setOnAction(e -> {
            String name = textField.getText();
            String checkName = "";
            try {
                checkName = checkSaveGame(name);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (!checkName.equals("")) {
                AtomicBoolean checker = secondPopUp(name, map);
                if (checker.get()){
                    popupwindow.close();
                }
            } else {
                createSaveGame(name, map);
                popupwindow.close();
            }
        });

        VBox layout= new VBox(10);


        layout.getChildren().add(label1);
        layout.getChildren().add(textField);
        layout.getChildren().add(button1);
        layout.getChildren().add(button2);

        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

    private String checkSaveGame(String name) throws IOException {
        String jsonData = "";
        try {
            String filePath = "src/main/resources/saves/" + name + ".json";
            jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (IOException e){
            jsonData = "";
        }
        return jsonData;
    }

    private void createSaveGame(String name, JsonObject map){
        Writer writer = null;
        try {
            writer = new FileWriter("src/main/resources/saves/"+name+".json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        new Gson().toJson(map, writer);
        try {
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private AtomicBoolean secondPopUp(String name, JsonObject map){
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Overwrite save");


        Label label1= new Label("Do you really want to overwrite your save game?");


        Button button1= new Button("No");

        Button button2 = new Button("Yes");
        AtomicBoolean checker = new AtomicBoolean(false);
        button1.setOnAction(e -> popupwindow.close());
        button2.setOnAction(e -> {
            createSaveGame(name, map);
            checker.set(true);
            popupwindow.close();
        });
        VBox layout= new VBox(10);


        layout.getChildren().add(label1);
        layout.getChildren().add(button1);
        layout.getChildren().add(button2);

        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 400, 350);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
        return checker;
    }

}