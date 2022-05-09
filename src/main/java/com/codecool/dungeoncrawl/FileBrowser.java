package com.codecool.dungeoncrawl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.codecool.dungeoncrawl.model.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileBrowser {
    private String path;
    private Main main;

    public FileBrowser(Main main) {

        this.main = main;
    }

    public void start() {
        Stage browser = new Stage();
        browser.setTitle("File Chooser Sample");

        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open saved game");
        final Button cancel = new Button("cancel");

        openButton.setOnAction(e -> {
                    File file = fileChooser.showOpenDialog(browser);
                    path = file.getPath();
            System.out.println(path);
                    browser.close();
                    load(path);

                });

        cancel.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        browser.close();
                    }
                });


        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(cancel, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, cancel);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        browser.setScene(new Scene(rootGroup));
        browser.show();
    }


    private void load(String filePath){
        try {

                    String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));

                    GameState gameState = main.generateGson().fromJson(jsonData, GameState.class);

                    main.loadGame(gameState);

                }catch (IOException ex) {
                    ex.printStackTrace();
                }
    }
}