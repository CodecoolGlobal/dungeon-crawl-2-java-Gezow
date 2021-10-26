package com.codecool.dungeoncrawl;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class PopUpWindow {


    public void display()
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
            String test = textField.getText();
            System.out.println(test);
            popupwindow.close();
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

}