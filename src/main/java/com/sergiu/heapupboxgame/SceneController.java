package com.sergiu.heapupboxgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void switchToLevel(ActionEvent event) throws IOException, URISyntaxException {
        Object node = event.getSource();
        Button b = (Button)node;
        int level = Integer.parseInt(b.getText());

        Object selectedLevel = getClass().getResource("levels/level-" + level+ "/level-" + level + ".fxml");
        String defaultLevel = "levels/level-0/level-0.fxml";
        if (selectedLevel == null) {
            root = FXMLLoader.load(getClass().getResource(defaultLevel));
        } else {
            root = FXMLLoader.load(getClass().getResource("levels/level-" + level+ "/level-" + level + ".fxml"));
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
