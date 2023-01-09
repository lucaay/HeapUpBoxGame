package com.sergiu.heapupboxgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLevelSelector(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("level-selector-screen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHomeScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home-screen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLevel(ActionEvent event) throws IOException {
        Object node = event.getSource(); //returns the object that generated the event
        //since the returned object is a Button you can cast it to one
        Button b = (Button)node;
        int level = Integer.parseInt(b.getText());
        root = FXMLLoader.load(getClass().getResource("levels/level-" + level + ".fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
